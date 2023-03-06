package sk.figlar.apod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import sk.figlar.apod.databinding.FragmentApodDetailBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@AndroidEntryPoint
class ApodDetailFragment : Fragment() {
    private var _binding: FragmentApodDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val viewModel: ApodDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApodDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            updateUi(getApod())
        }
    }

    private suspend fun getApod(): ApodDomainModel = viewModel.getApod()

    private fun updateUi(apod: ApodDomainModel) {
        binding.apply {
            apodImage.load(apod.url)

            val localDate = LocalDate.parse(apod.date)
            val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
            val formattedLocalDate = localDate.format(formatter)

            apodTitle.text = getString(
                R.string.apod_title,
                apod.title,
                formattedLocalDate,
                apod.copyright
            )
            apodExplanation.text = apod.explanation
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}