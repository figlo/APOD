package com.example.apod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.window.layout.WindowMetricsCalculator
import com.example.apod.databinding.FragmentApodGalleryBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ApodGalleryFragment : Fragment() {
    private var _binding: FragmentApodGalleryBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val viewModel: ApodGalleryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApodGalleryBinding.inflate(inflater)
        binding.apodGrid.layoutManager = GridLayoutManager(context, 3)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // calculating item size (width and height)
        val bounds = WindowMetricsCalculator
            .getOrCreate()
            .computeCurrentWindowMetrics(requireActivity())
            .bounds
        val displayWidth = bounds.width()
        val itemWidth: Int = displayWidth / 3

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.dbApodsFlow.collect { items ->
                    binding.apodGrid.adapter = ApodListAdapter(items, itemWidth) { position -> onItemClick(position) }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onItemClick(position: Int) {
        Snackbar.make(requireView(), "aaa", Snackbar.LENGTH_LONG).show()
    }
}