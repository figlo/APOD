package com.example.apod

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apod.api.ApodApi
import com.example.apod.databinding.FragmentApodGalleryBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import timber.log.Timber

class ApodGalleryFragment() : Fragment() {

    private var _binding: FragmentApodGalleryBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApodGalleryBinding.inflate(inflater)
        binding.apodGrid.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/planetary/apod/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val apodApi: ApodApi = retrofit.create<ApodApi>()

        viewLifecycleOwner.lifecycleScope.launch {
            val response = apodApi.fetchContents()
            Log.d("TAG", "Log response2 received: $response")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}