package com.example.android.shoppingList.dados

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.android.shoppingList.apresentacao.model.ItensLista
import com.example.android.shoppingList.apresentacao.model.Produto
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras
import com.example.android.shoppingList.apresentacao.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Este arquivo representa o BackEnd (Database) da Aplicação
 */
@Database(entities = [ListaDeCompras::class, Produto::class, ItensLista::class, Usuario::class], version = 1)
abstract class ListasRoomDatabase : RoomDatabase() {

    abstract fun listaDao(): ListaDeComprasDao
    abstract fun produtosDao(): ProdutosDao
    abstract fun itensListaDao(): ItensListaDao
    abstract fun usuarioDao(): UsuarioDao

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
                        populateDatabase(database.listaDao(),database.produtosDao(),database.itensListaDao(),database.usuarioDao())
                    }
                }
            }
        }

        /**
         * Popula o BD com uma lista de exemplo
         */
        suspend fun populateDatabase(listaDeComprasDao: ListaDeComprasDao, produtosDao: ProdutosDao, itensListaDao: ItensListaDao, usuarioDao: UsuarioDao) {
            // Starta o app com um DB clean
            listaDeComprasDao.deleteAll()
            var listaDeCompras = ListaDeCompras(1,"Lista de Exemplo",0)
            listaDeComprasDao.insert(listaDeCompras)

            produtosDao.deleteAll()
            var produto_exemplo_1 = Produto(1,"Leite", "Latcinios")
            var produto_exemplo_2 = Produto(2,"Frango","Carnes")
            var produto_exemplo_3 = Produto(3,"Refrigerante","Bebidas")
            var produto_exemplo_4 = Produto(4,"Sabonete","Higiene")
            var produto_exemplo_5 = Produto(5,"Shampoo","Higiene")
            var produto_exemplo_6 = Produto(6,"Amaciante","Produtos de Limpeza" )
            var produto_exemplo_7 = Produto(7,"Suco","Bebidas" )
            var produto_exemplo_8 = Produto(8,"Cerveja","Bebidas" )
            var produto_exemplo_9 = Produto(9, "Torrada","Mercearia")
            var produto_exemplo_10 = Produto(10, "Pó de café","Mercearia")
            var produto_exemplo_11 = Produto(11,"Cacau em pó","Mercearia")
            var produto_exemplo_12 = Produto(12,"Amido de milho ","Mercearia")
            var produto_exemplo_13 = Produto(13,"Sabão liquido","Mercearia")
            var produto_exemplo_14 = Produto(14,"Detergente","Produtos de limpeza")
            var produto_exemplo_15 = Produto(15,"Sabão em pó","Produtos de limpeza")
            var produto_exemplo_16 = Produto(16,"Maçã","Hortifruti")
            var produto_exemplo_17 = Produto(17,"Banana","Hortifruti")
            var produto_exemplo_18 = Produto(18,"Limão","Hortifruti")
            var produto_exemplo_19 = Produto(19,"Melancia","Hortifruti")
            var produto_exemplo_20 = Produto(20,"Batata","Hortifruti")
            var produto_exemplo_21 = Produto(21,"Lasanha","Hortifruti")


            produtosDao.insert(produto_exemplo_1)
            produtosDao.insert(produto_exemplo_2)
            produtosDao.insert(produto_exemplo_3)
            produtosDao.insert(produto_exemplo_4)
            produtosDao.insert(produto_exemplo_5)
            produtosDao.insert(produto_exemplo_6)
            produtosDao.insert(produto_exemplo_7)
            produtosDao.insert(produto_exemplo_8)
            produtosDao.insert(produto_exemplo_9)
            produtosDao.insert(produto_exemplo_10)
            produtosDao.insert(produto_exemplo_11)
            produtosDao.insert(produto_exemplo_12)
            produtosDao.insert(produto_exemplo_13)
            produtosDao.insert(produto_exemplo_14)
            produtosDao.insert(produto_exemplo_15)
            produtosDao.insert(produto_exemplo_16)
            produtosDao.insert(produto_exemplo_17)
            produtosDao.insert(produto_exemplo_18)
            produtosDao.insert(produto_exemplo_19)
            produtosDao.insert(produto_exemplo_20)
            produtosDao.insert(produto_exemplo_21)


            var item_carrinho = ItensLista(1,1,1,"Leite","Latcinios")
            var item_carrinho_2 = ItensLista(2,1,2, "Frango","Carnes")
            var item_carrinho_3 = ItensLista(3,1,7,"Suco","Bebidas")
            var item_carrinho_4 = ItensLista(4,1,5,"Shampoo", "Higiene")
            var item_carrinho_5 = ItensLista(5,1,8,"Cerveja","Bebidas")

            itensListaDao.insert(item_carrinho)
            itensListaDao.insert(item_carrinho_2)
            itensListaDao.insert(item_carrinho_3)
            itensListaDao.insert(item_carrinho_4)
            itensListaDao.insert(item_carrinho_5)

            var default_user: Usuario = Usuario(0,"Convidado", "teste","teste","123","Usuario")
            usuarioDao.insertUserData(default_user)
        }
    }
}
