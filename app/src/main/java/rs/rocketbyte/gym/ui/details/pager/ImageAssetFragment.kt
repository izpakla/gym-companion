package rs.rocketbyte.gym.ui.details.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import rs.rocketbyte.gym.commons.image.ImageLoader
import rs.rocketbyte.gym.databinding.FragmentImageAssetBinding
import rs.rocketbyte.gym.ui.commons.BindingFragment

class ImageAssetFragment : BindingFragment<FragmentImageAssetBinding>() {

    companion object {
        fun newInstance(imageUrl: String): Fragment {
            return ImageAssetFragment().apply {
                arguments = Bundle().apply {
                    putString("image", imageUrl)
                }
            }
        }
    }

    private val imageUrl by lazy { requireArguments().getString("image") ?: "" }

    override fun onBinderCreate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentImageAssetBinding = FragmentImageAssetBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ImageLoader.load(requireContext(), imageUrl, binding.imageView)
    }

}