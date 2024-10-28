package com.non.mornhouse.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "numbers")
data class Number(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "number")
    val number: Int?,

    @ColumnInfo(name = "text")
    val text: String
)


