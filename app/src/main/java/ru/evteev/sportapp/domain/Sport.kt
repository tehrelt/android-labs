package ru.evteev.sportapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Sport(@PrimaryKey val id: UUID = UUID.randomUUID(),
                 var name: String = "");