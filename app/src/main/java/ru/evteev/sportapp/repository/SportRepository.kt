package ru.evteev.sportapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room.databaseBuilder
import ru.evteev.sportapp.database.SportDAO
import ru.evteev.sportapp.database.SportDatabase
import ru.evteev.sportapp.domain.Sport
import java.util.UUID
import java.util.concurrent.Executors

class SportRepository(private val sportDao: SportDAO) {
    fun insertSport(sport: Sport) = sportDao.addSport(sport);
    fun getSports(): LiveData<List<Sport>> = sportDao.getSports();
    fun getSport(id: Int): LiveData<Sport> = sportDao.getSport(id);
    fun updateSport(sport: Sport) = sportDao.updateSport(sport);
}