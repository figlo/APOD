package sk.figlar.apod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
import androidx.window.layout.WindowMetricsCalculator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import sk.figlar.apod.databinding.FragmentApodGalleryBinding

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
        _binding = FragmentApodGalleryBinding.inflate(inflater, container, false)
        with(binding.apodGrid) {
            layoutManager = GridLayoutManager(context, 3)
            setHasFixedSize(true)
        }
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

        val adapter = ApodListAdapter(itemWidth) { apodId ->
            findNavController().navigate(ApodGalleryFragmentDirections.actionApodGalleryFragmentToApodDetailFragment(apodId))
        }
        adapter.stateRestorationPolicy = PREVENT_WHEN_EMPTY
        binding.apodGrid.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.refreshApods()
                viewModel.apodsFlow.collect { apods ->
                    adapter.submitList(apods)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}