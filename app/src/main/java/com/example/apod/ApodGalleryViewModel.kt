package com.example.apod

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apod.db.ApodDao
import com.example.apod.db.toDomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApodGalleryViewModel @Inject constructor(
    dao: ApodDao,
) : ViewModel() {
    private val apodRepository = ApodRepository(dao)

    private var _dbApodsFlow: MutableStateFlow<List<ApodDomainModel>> = MutableStateFlow(emptyList())
    val dbApodsFlow: StateFlow<List<ApodDomainModel>>
        get() = _dbApodsFlow.asStateFlow()

    init {
        viewModelScope.launch {
            apodRepository.refreshApods()
            apodRepository.getDbApodsFlow().collect {
                _dbApodsFlow.value = it.toDomainModel()
            }
        }
    }
}