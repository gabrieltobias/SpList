package com.example.android.shoppingList

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.shoppingList.dados.ListasRoomDatabase
import com.example.android.shoppingList.apresentacao.model.ListaDeCompras
import com.example.android.shoppingList.dados.ListaDeComprasDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4::class)
class ListaDeComprasDaoTest {

    private lateinit var listaDeComprasDao: ListaDeComprasDao
    private lateinit var db: ListasRoomDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, ListasRoomDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        listaDeComprasDao = db.listaDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetWord() = runBlocking {
        val listaDeCompras = ListaDeCompras("NomeLista")
        listaDeComprasDao.insert(listaDeCompras)
        val allWords = listaDeComprasDao.GetListas().first()
        assertEquals(allWords[0].NomeLista, listaDeCompras.NomeLista)
    }

    @Test
    @Throws(Exception::class)
    fun getAllWords() = runBlocking {
        val listaDeCompras = ListaDeCompras("aaa")
        listaDeComprasDao.insert(listaDeCompras)
        val listaDeCompras2 = ListaDeCompras("bbb")
        listaDeComprasDao.insert(listaDeCompras2)
        val allWords = listaDeComprasDao.GetListas().first()
        assertEquals(allWords[0].NomeLista, listaDeCompras.NomeLista)
        assertEquals(allWords[1].NomeLista, listaDeCompras2.NomeLista)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAll() = runBlocking {
        val listaDeCompras = ListaDeCompras("NomeLista")
        listaDeComprasDao.insert(listaDeCompras)
        val listaDeCompras2 = ListaDeCompras("word2")
        listaDeComprasDao.insert(listaDeCompras2)
        listaDeComprasDao.deleteAll()
        val allWords = listaDeComprasDao.GetListas().first()
        assertTrue(allWords.isEmpty())
    }
}
