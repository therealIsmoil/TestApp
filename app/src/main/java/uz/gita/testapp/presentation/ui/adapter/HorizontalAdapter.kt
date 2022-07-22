package uz.gita.testapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import uz.gita.testapp.R
import uz.gita.testapp.data.model.UserData
import uz.gita.testapp.databinding.ItemHorizontalBinding

class HorizontalAdapter :
    ListAdapter<UserData, HorizontalAdapter.HorizontalViewHolder>(HorizontalDiffUtil) {
    private var onClickItemListener: ((UserData) -> Unit)? = null

    private object HorizontalDiffUtil : DiffUtil.ItemCallback<UserData>() {
        override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean =
            oldItem == newItem
    }

    inner class HorizontalViewHolder(private val binding: ItemHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onClickItemListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun bind() {
            Glide.with(binding.root)
                .load(getItem(absoluteAdapterPosition).download_url)
                .placeholder(R.drawable.place_holder)
                .centerCrop()
                .apply(RequestOptions().override(100, 100))
                .error(R.drawable.ic_launcher_background)
                .into(binding.imageHorizontal)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        return HorizontalViewHolder(
            ItemHorizontalBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        holder.bind()
    }

    fun setOnClickItemListener(block: (UserData) -> Unit) {
        onClickItemListener = block
    }
}