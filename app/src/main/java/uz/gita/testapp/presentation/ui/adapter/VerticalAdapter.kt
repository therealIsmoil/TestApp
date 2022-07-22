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
import uz.gita.testapp.databinding.ItemVerticalBinding
import uz.gita.testapp.utils.changeSize

class VerticalAdapter :
    ListAdapter<UserData, VerticalAdapter.VerticalViewHolder>(VerticalDiffUtil) {
    private var onClickItemListener: ((UserData) -> Unit)? = null

    private object VerticalDiffUtil : DiffUtil.ItemCallback<UserData>() {
        override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean =
            oldItem == newItem
    }

    inner class VerticalViewHolder(private val binding: ItemVerticalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onClickItemListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun bind() {
            Glide.with(binding.root)
                .load(getItem(absoluteAdapterPosition).download_url.changeSize())
                .apply(RequestOptions().override(100, 100))
                .placeholder(R.drawable.place_holder)
                .centerCrop()
                .error(R.drawable.ic_launcher_background)
                .into(binding.imageVertical)

            binding.nameVertical.text = getItem(absoluteAdapterPosition).author
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        return VerticalViewHolder(
            ItemVerticalBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_vertical, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) {
        holder.bind()
    }

    fun setOnClickItemListener(block: (UserData) -> Unit) {
        onClickItemListener = block
    }
}