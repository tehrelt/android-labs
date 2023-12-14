package ru.evteev.sportapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.evteev.sportapp.domain.Sport
import ru.evteev.sportapp.repository.SportRepository
import java.util.UUID

class SportDetailViewModel : ViewModel() {
    private val sportRepository = SportRepository.get()
    private val sportIdLiveData = MutableLiveData<UUID>()

    var sportLiveData: LiveData<Sport?> = Transformations
}