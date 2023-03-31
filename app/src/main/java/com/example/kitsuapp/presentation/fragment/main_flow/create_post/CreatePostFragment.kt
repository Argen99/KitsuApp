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

/**
 * [CreatePostFragment] наследуется от [BaseFragment] и содержит реализацию функционала
 * создания поста. Он также содержит приватную переменную user, которая используется для
 * получения информации о пользователе, создающем пост.
 * @author Argen
 * @since 1.0v
 */
class CreatePostFragment :
    BaseFragment<FragmentCreatePostBinding, CreatePostViewModel>(R.layout.fragment_create_post) {
    override val binding by viewBinding(FragmentCreatePostBinding::bind)
    override val viewModel by viewModel<CreatePostViewModel>()

    private var user: UserUI? = null

    /**
     * Метод [setupListeners] используется для настройки обработчиков событий пользовательского ввода.
    */
    override fun setupListeners() {
        binding.tvPost.setOnClickListener {
            createPost()
        }
        binding.tvCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    /**
     * [setupObservers] Тут опять из за бэка приходится писать фигню (⊙_⊙),
     * идет запрос на текущего пользователя по никнейму,
     * [subscribeToUser] в ответе получаем список пользователей с похожими никнеймами
     */
    override fun setupObservers() {
        viewModel.getUser("choni17")
        subscribeToCreatePostState()
        subscribeToUser()
    }

    /**
     * Метод [subscribeToUser] используется для подписки на изменения состояний при получении
    * информации о пользователе. Если получение происходит успешно, то происходит заполнение
    * полей фрагмента информацией о пользователе. Если получение происходит неудачно,
    * то выводится сообщение об ошибке.
     */
    private fun subscribeToUser() {
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

    /**
     * Метод [subscribeToCreatePostState] используется для подписки на изменения состояний при
     * создании поста. Если создание поста происходит успешно, то происходит перенаправление на
     * предыдущий экран. Если создание поста происходит неудачно, то выводится сообщение об ошибке.
     */
    private fun subscribeToCreatePostState() {
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
    }

    /**
      [createPost] Функция для создания нового поста.
      Если ID пользователя равен null то выводится сообщение об ошибке.
      Если поле ввода контента пустое или равно null, выводится сообщение об ошибке.
      Иначе вызывается метод createPost у экземпляра ViewModel с передачей параметров:
      userId - ID пользователя,
      content - контент поста,
      nsfw - флаг, указывающий на то, является ли пост NSFW,
      spoiler - флаг, указывающий на то, содержит ли пост спойлеры.
     */
    private fun createPost() {
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
}


