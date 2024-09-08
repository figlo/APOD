package sk.figlar.apod

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ApodDetailViewModel @Inject constructor(
    private val apodRepository: ApodRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // todo maybe get apod in init{}

    private val navArgs = ApodDetailFragmentArgs.fromSavedStateHandle(savedStateHandle)
    private val apodId = navArgs.apodId

    suspend fun getApod(): ApodDomainModel {
        return apodRepository.getApod(apodId)!!
    }
}