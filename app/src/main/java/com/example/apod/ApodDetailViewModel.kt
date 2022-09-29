package com.example.apod

import androidx.lifecycle.ViewModel
import com.example.apod.db.ApodDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ApodDetailViewModel @Inject constructor(
    dao: ApodDao
) : ViewModel() {
}