package sk.figlar.apod

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApodDetailViewModel @Inject constructor(
    apodRepository: ApodRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val apodId: Long = savedStateHandle["apodId"] ?: -1

    private val _apod: MutableStateFlow<ApodDomainModel?> = MutableStateFlow(null)
    val apod: StateFlow<ApodDomainModel?>
        get() = _apod.asStateFlow()

    init {
        viewModelScope.launch {
            _apod.value = apodRepository.getApod(apodId)
        }
    }
}