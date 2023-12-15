package ru.evteev.sportapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ru.evteev.sportapp.database.SportDatabase
import ru.evteev.sportapp.domain.Sport
import ru.evteev.sportapp.repository.SportRepository
import java.util.UUID

class SportsListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: SportRepository
    private val sports: LiveData<List<Sport>>

    fun getSport(id: Int): LiveData<Sport> = repository.getSport(id)
    fun getSports(): LiveData<List<Sport>> = sports;
    fun insert(name: String) {
        repository.insertSport(Sport(0, name));
    }

    init {
        val dao = SportDatabase.get(application).sportDao();
        repository = SportRepository(dao);
        sports = repository.getSports()
    }
}