package rs.rocketbyte.gym.ui.details.pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ImageStateAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    private val items: MutableList<String> = ArrayList()

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment =
        ImageAssetFragment.newInstance(items[position])

    fun setItems(items: List<String>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}