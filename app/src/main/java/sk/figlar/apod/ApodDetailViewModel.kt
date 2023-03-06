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

    private val apodId: Long = savedStateHandle["apodId"] ?: -1

    suspend fun getApod(): ApodDomainModel {
        return apodRepository.getApod(apodId)!!
    }
}