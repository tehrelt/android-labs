package ru.evteev.sportapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room.databaseBuilder
import ru.evteev.sportapp.database.SportDatabase
import ru.evteev.sportapp.domain.Sport
import java.util.UUID
import java.util.concurrent.Executors

class SportRepository private constructor(context: Context) {
    private val database: SportDatabase = databaseBuilder<SportDatabase>(
        context.applicationContext,
        SportDatabase::class.java,
        "sport"
    ).build();

    private val sportDao = database.sportDao()
    private val executor = Executors.newSingleThreadExecutor()
    fun getSports(): LiveData<List<Sport>> = sportDao.getSports();
    fun getSport(id: UUID): LiveData<Sport> = sportDao.getSport(id);

    companion object {
        private var INSTANCE: SportRepository? = null;
    }


}