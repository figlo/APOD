package com.example.apod

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apod.api.GalleryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class ApodGalleryViewModel : ViewModel() {
    private val apodRepository = ApodRepository()

    private val _galleryItems: MutableStateFlow<List<GalleryItem>> = MutableStateFlow(emptyList())
    val galleryItems: StateFlow<List<GalleryItem>>
        get() = _galleryItems.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _galleryItems.value = apodRepository
                    .fetchApods()
                    .filter { it.mediaType == "image"}
                    .reversed()
            } catch (ex: Exception) {
                Timber.e("Failed to fetch gallery items", ex)
            }
        }
    }
}