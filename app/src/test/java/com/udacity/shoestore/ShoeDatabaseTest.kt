package com.udacity.shoestore

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.udacity.shoestore.data.Shoe
import com.udacity.shoestore.data.ShoeDatabase
import com.udacity.shoestore.data.ShoeDatabaseDao
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class ShoeDatabaseTest {

    private lateinit var shoeDao: ShoeDatabaseDao
    private lateinit var db: ShoeDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, ShoeDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        shoeDao = db.shoeDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    suspend fun insertAndGetShoe() {
        val shoe = Shoe(0L, "", "", 11.5, "")
        shoeDao.insert(shoe)
        val lastShoe = shoeDao.getLastShoe()
        assertEquals(lastShoe?.size, 11.5)

    }

}