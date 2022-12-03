package sk.figlar.apod

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sk.figlar.apod.db.toDomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApodGalleryViewModel @Inject constructor(
    private val apodRepository: ApodRepository,
) : ViewModel() {

    private var _apodsFlow: MutableStateFlow<List<ApodDomainModel>> = MutableStateFlow(emptyList())
    val apodsFlow: StateFlow<List<ApodDomainModel>>
        get() = _apodsFlow.asStateFlow()

    init {
        viewModelScope.launch {
            apodRepository.getApodDbModelsFlow().collect { apodDbModelList ->
                _apodsFlow.value = apodDbModelList.filter { apodDbModel ->
                    apodDbModel.mediaType == "image"
                }.map { it.toDomainModel() }
            }
        }
    }

    suspend fun refreshApods() {
        apodRepository.refreshApods()
    }
}