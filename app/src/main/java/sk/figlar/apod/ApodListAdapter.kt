package sk.figlar.apod

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import sk.figlar.apod.databinding.ListItemGalleryBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class ApodViewHolder(
    private val binding: ListItemGalleryBinding,
    private val itemWidth: Int,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(apodDomainModel: ApodDomainModel, onItemClicked: (apodId: Long) -> Unit) {
        with(binding) {
            itemImageView.layoutParams.width = itemWidth
            itemImageView.layoutParams.height = itemWidth
            itemImageView.load(apodDomainModel.url) {
                placeholder(R.drawable.placeholder_24dp)
            }

            val localDate = LocalDate.parse(apodDomainModel.date)
            val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
            val formattedLocalDate = localDate.format(formatter)
            itemTextView.text = Strings.get(R.string.apod_detail_title, apodDomainModel.title, formattedLocalDate)

            root.setOnClickListener { onItemClicked(apodDomainModel.id) }
        }
    }
}

class ApodListAdapter(
    private val apods: List<ApodDomainModel>,
    private val itemWidth: Int,
    private val onItemClicked: (apodId: Long) -> Unit,
) : RecyclerView.Adapter<ApodViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ApodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemGalleryBinding.inflate(inflater, parent, false)
        return ApodViewHolder(binding, itemWidth)
    }

    override fun onBindViewHolder(holder: ApodViewHolder, position: Int) {
        val item = apods[position]
        holder.bind(item, onItemClicked)
    }

    override fun getItemCount() = apods.size
}