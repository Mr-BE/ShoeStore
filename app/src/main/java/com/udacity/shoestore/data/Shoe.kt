package com.udacity.shoestore.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shoe_table")
data class Shoe(
    @PrimaryKey(autoGenerate = true)
    var shoeId: Long = 0L,

    @ColumnInfo(name = "shoe_name")
    var name: String,

    @ColumnInfo(name = "shoe_company_name")
    var company: String,

    @ColumnInfo(name = "shoe_size")
    var size: Double,

    @ColumnInfo(name = "shoe_description")
    var description: String,

    )
