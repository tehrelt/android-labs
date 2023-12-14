package ru.evteev.sportapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.evteev.sportapp.database.SportDatabase
import ru.evteev.sportapp.repository.SportRepository
import java.util.UUID

class SportDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: SportRepository;

    init {
        val dao = SportDatabase.get(application).sportDao();
        repository = SportRepository(dao);
    }
}