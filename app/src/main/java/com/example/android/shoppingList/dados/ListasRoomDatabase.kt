package com.example.android.shoppingList.dados

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.android.shoppingList.apresentacao.model.Produto
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Este arquivo representa o BackEnd (Database) da Aplicação
 */
@Database(entities = [ListaDeCompras::class, Produto::class], version = 1)
abstract class ListasRoomDatabase : RoomDatabase() {

    abstract fun listaDao(): ListaDeComprasDao
    abstract fun produtosDao(): ProdutosDao

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
                        populateDatabase(database.listaDao(),database.produtosDao())
                    }
                }
            }
        }

        /**
         * Popula o BD com uma lista de exemplo
         */
        suspend fun populateDatabase(listaDeComprasDao: ListaDeComprasDao, produtosDao: ProdutosDao) {
            // Starta o app com um DB clean
            listaDeComprasDao.deleteAll()
            var listaDeCompras = ListaDeCompras(1,"Lista de Exemplo")
            listaDeComprasDao.insert(listaDeCompras)

            produtosDao.deleteAll()
            var produto_exemplo_1 = Produto(1,"Leite", "Latcinios")
            var produto_exemplo_2 = Produto(2,"Frango","Carnes" )
            var produto_exemplo_3 = Produto(3,"Refrigerante","Bebidas" )
            var produto_exemplo_4 = Produto(4,"Sabonete","Higiene" )
            var produto_exemplo_5 = Produto(5,"Shampoo","Higiene" )
            var produto_exemplo_6 = Produto(6,"Amaciante","Higiene" )
            var produto_exemplo_7 = Produto(7,"Ração de cachorro","Outros" )
            var produto_exemplo_8 = Produto(8,"Cerveja","Bebidas" )

            produtosDao.insert(produto_exemplo_1)
            produtosDao.insert(produto_exemplo_2)
            produtosDao.insert(produto_exemplo_3)
            produtosDao.insert(produto_exemplo_4)
            produtosDao.insert(produto_exemplo_5)
            produtosDao.insert(produto_exemplo_6)
            produtosDao.insert(produto_exemplo_7)
            produtosDao.insert(produto_exemplo_8)

        }
    }
}
