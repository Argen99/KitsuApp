package com.example.kitsuapp.presentation.fragment.sign_flow.board

import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.data.local.prefs.TokenManager
import com.example.kitsuapp.R
import com.example.kitsuapp.core.base.BaseFragment
import com.example.kitsuapp.core.base.BaseViewModel
import com.example.kitsuapp.core.extension.gone
import com.example.kitsuapp.core.extension.navigateSafely
import com.example.kitsuapp.core.extension.visible
import com.example.kitsuapp.databinding.FragmentBoardBinding
import com.example.kitsuapp.presentation.adapter.OnBoardAdapter
import org.koin.android.ext.android.inject

/**
 * [BoardFragment] наследуется от класса BaseFragment, который является базовым классом
 * для фрагментов в приложении. Класс BoardFragment используется для отображения экрана
 * Onboarding в приложении, который отображает экран входа в приложение иобеспечивает
 * навигацию пользователя по первоначальной информации о приложении.
 * @author Argen
 * @since 1.0v
 */
class BoardFragment : BaseFragment<FragmentBoardBinding, BaseViewModel>(R.layout.fragment_board) {
    /**
     * Поле [binding] определяет экземпляр класса FragmentBoardBinding, который представляет
      * макет фрагмента. Поле [viewModel] не используется в данном классе.
     */
    override val binding by viewBinding(FragmentBoardBinding::bind)
    override lateinit var viewModel: BaseViewModel
    private val tokenManager: TokenManager by inject()
    /**
     * Метод [initialize] используется для инициализации элементов пользовательского интерфейса.
     * В данном случае, установлен адаптер OnBoardAdapter для ViewPager, а WormDotsIndicator
     * прикреплен к ViewPager.
     */
    override fun initialize() {
        binding.viewPagerOnboard.adapter = OnBoardAdapter(requireContext())
        binding.wormDotsIndicator.attachTo(binding.viewPagerOnboard)
    }

    /**
     * Метод [setupListeners] используется для настройки обработчиков событий пользовательского
     * ввода. В данном случае, установлены обработчики для кнопок btnNext и btnStart, которые
     * перемещают ViewPager на следующий экран и переходят на фрагмент LoginFragment соответственно.
     * Также установлен обратный вызов для ViewPager, который скрывает кнопку btnNext и отображает
     * кнопку btnStart при достижении последнего экрана в ViewPager.
     */
    override fun setupListeners() {

        binding.btnNext.setOnClickListener {
            binding.viewPagerOnboard.currentItem++
        }

        binding.tvSkip.setOnClickListener {
            tokenManager.onBoardIsShown = true
            findNavController().navigateSafely(R.id.action_boardFragment_to_loginFragment)
        }

        binding.btnStart.setOnClickListener {
            tokenManager.onBoardIsShown = true
            findNavController().navigateSafely(R.id.action_boardFragment_to_loginFragment)
        }

        binding.viewPagerOnboard.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 2) {
                    binding.btnStart.visible()
                    binding.btnNext.gone()
                } else {
                    binding.btnStart.gone()
                    binding.btnNext.visible()
                }
            }
        })
    }
}