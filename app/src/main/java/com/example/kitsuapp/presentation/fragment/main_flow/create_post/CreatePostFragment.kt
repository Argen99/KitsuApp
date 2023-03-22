package com.example.kitsuapp.presentation.fragment.main_flow.create_post

import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kitsuapp.R
import com.example.kitsuapp.core.base.BaseFragment
import com.example.kitsuapp.core.extension.gone
import com.example.kitsuapp.core.extension.loadImage
import com.example.kitsuapp.core.extension.showToast
import com.example.kitsuapp.core.extension.visible
import com.example.kitsuapp.databinding.FragmentCreatePostBinding
import com.example.kitsuapp.model.UserUI
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatePostFragment :
    BaseFragment<FragmentCreatePostBinding, CreatePostViewModel>(R.layout.fragment_create_post) {
    override val binding by viewBinding(FragmentCreatePostBinding::bind)
    override val viewModel by viewModel<CreatePostViewModel>()

    private var user: UserUI? = null

    override fun setupListeners() {
        binding.tvPost.setOnClickListener {
            if (user?.id == null) {
                showToast(getString(R.string.unknown_error))
            } else if (binding.etContent.text.isNullOrBlank()) {
                binding.etContent.error = getString(R.string.enter_text)
            } else {
                viewModel.createPost(
                    userId = user!!.id!!,
                    content = binding.etContent.text.toString(),
                    nsfw = binding.cbNsfw.isChecked,
                    spoiler = binding.cbSpoiler.isChecked
                )
            }
        }

        binding.tvCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun setupObservers() {
        viewModel.getUser("choni17")

        viewModel.getCreatePostState.spectateUiState(
            loading = {
                binding.pbCreatePost.visible()
            },
            success = {
                binding.pbCreatePost.gone()
                showToast("onSuccess")
                findNavController().navigateUp()
            },
            error = {
                binding.pbCreatePost.gone()
                showToast(it)
            }
        )

        viewModel.userFlow.spectateUiState(
            loading = { binding.pbCreatePost.visible() },
            success = { data ->
                binding.pbCreatePost.gone()
                user = data.first { user ->
                    user.attributes?.name == "choni17"
                }
                user?.attributes?.name?.let { binding.tvUserName.text = it }
                if (user?.attributes?.avatar?.original != null) {
                    binding.ivUserImage.loadImage(user?.attributes?.avatar?.original!!)
                } else {
                    binding.ivUserImage.setImageResource(R.drawable.default_avatar)
                }
            },
            error = { showToast(it) }
        )
    }
}