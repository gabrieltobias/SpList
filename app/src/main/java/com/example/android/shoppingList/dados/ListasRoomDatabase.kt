package com.example.android.shoppingList.dados

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.android.shoppingList.apresentacao.model.Item
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Este arquivo representa o BackEnd (Database) da Aplicação
 */
@Database(entities = [ListaDeCompras::class, Item::class], version = 1)
abstract class ListasRoomDatabase : RoomDatabase() {

    abstract fun listaDao(): ListaDeComprasDao
    abstract fun itensDao(): ItensDao

    companion object {
        @Volatile
        private var INSTANCE: ListasRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ListasRoomDatabase {
            //Se a instancia não for vazia, retorna
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ListasRoomDatabase::class.java,
                    "spList_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(ListasDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // retorna a instancia do BD
                instance
            }
        }

        private class ListasDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Popula o BD dando um override em onCreate
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.listaDao(),database.itensDao())
                    }
                }
            }
        }

        /**
         * Popula o BD com uma lista de exemplo
         */
        suspend fun populateDatabase(listaDeComprasDao: ListaDeComprasDao, itensDao: ItensDao) {
            // Starta o app com um DB clean
            listaDeComprasDao.deleteAll()
            var listaDeCompras = ListaDeCompras(1,"Lista de Exemplo")
            listaDeComprasDao.insert(listaDeCompras)

            itensDao.deleteAll()
            var item_exemplo_1 = Item(1,"Leite", "Latcinios")
            var item_exemplo_2 = Item(2,"Frango","Carnes" )
            itensDao.insert(item_exemplo_1)
            itensDao.insert(item_exemplo_2)

        }
    }
}
