package ru.evteev.sportapp.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "sports")
data class Sport(
    @PrimaryKey(autoGenerate = true )
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String = ""
)