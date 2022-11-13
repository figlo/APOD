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
    apodRepository: ApodRepository,
) : ViewModel() {

    private var _dbApodsFlow: MutableStateFlow<List<ApodDomainModel>> = MutableStateFlow(emptyList())
    val dbApodsFlow: StateFlow<List<ApodDomainModel>>
        get() = _dbApodsFlow.asStateFlow()

    init {
        viewModelScope.launch {
            apodRepository.refreshApods()
            apodRepository.getDbApodsFlow().collect { apodDbModelList ->
                _dbApodsFlow.value = apodDbModelList.filter { apodDbModel ->
                    apodDbModel.mediaType == "image"
                }.map { it.toDomainModel() }
            }
        }
    }
}