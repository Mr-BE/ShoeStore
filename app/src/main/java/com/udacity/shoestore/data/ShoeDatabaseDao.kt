package com.udacity.shoestore.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ShoeDatabaseDao {

    //insert shoes
    @Insert
    suspend fun insert(shoe: Shoe)

    //update shoe properties
    @Update
    suspend fun update(shoe: Shoe)

    //get specific shoe by id
    @Query("SELECT * from shoe_table WHERE shoeId = :key")
    suspend fun get(key: Long): Shoe?

    //clear shoe table
    @Query("DELETE FROM shoe_table")
    suspend fun clear()

    //get all shoes
    @Query("SELECT * FROM shoe_table ORDER BY shoeId DESC")
    fun getAllShoes(): LiveData<List<Shoe>>

    //get last shoe
    @Query("SELECT * FROM shoe_table ORDER BY shoeId DESC LIMIT 1")
    fun getLastShoe(): Shoe?

}
