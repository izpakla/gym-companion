package rs.rocketbyte.gym.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import rs.rocketbyte.core.model.Workout
import rs.rocketbyte.gym.commons.image.ImageLoader
import rs.rocketbyte.gym.databinding.ItemWorkoutBinding

class HomeAdapter(private val onItemAction: OnItemAction<Workout>) :
    RecyclerView.Adapter<HomeAdapter.ItemViewHolder>() {

    class ItemViewHolder(
        val itemWorkoutBinding: ItemWorkoutBinding,
        private val onItemAction: OnItemAction<Workout>
    ) : ViewHolder(itemWorkoutBinding.root) {
        init {
            itemWorkoutBinding.cardView.setOnClickListener {
                val workout: Workout = it.tag as? Workout ?: return@setOnClickListener
                onItemAction.onSelect(workout)
            }
            itemWorkoutBinding.cardView.setOnLongClickListener {
                val workout: Workout = it.tag as? Workout ?: return@setOnLongClickListener false
                onItemAction.onShare(workout)
                true
            }
        }
    }

    private val items: MutableList<Workout> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            ItemWorkoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemAction
        )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val workout = items[position]

        holder.itemWorkoutBinding.textTitle.text = workout.name
        ImageLoader.loadCrop(
            holder.itemView.context,
            workout.coverImage,
            holder.itemWorkoutBinding.imageBanner
        )
        holder.itemWorkoutBinding.cardView.tag = workout
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Workout>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    interface OnItemAction<T> {
        fun onSelect(item: T)
        fun onShare(item: T)
    }
}