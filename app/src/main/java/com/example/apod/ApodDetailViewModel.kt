package com.example.apod

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apod.db.ApodDao
import com.example.apod.db.ApodDbModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApodDetailViewModel @Inject constructor(
    dao: ApodDao,
) : ViewModel() {
    private val apodRepository = ApodRepository(dao)
    var apodId: Long = 0        // TODO

    private val _apod: MutableStateFlow<ApodDbModel?> = MutableStateFlow(null)
    val apod: StateFlow<ApodDbModel?>
        get() = _apod.asStateFlow()

    init {
        viewModelScope.launch {
            _apod.value = apodRepository.getApod(apodId)
        }
    }
}