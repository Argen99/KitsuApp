package com.example.kitsuapp.core.extension

import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * Расширение класса [Fragment] showToast для вывода короткого Toast сообщения
 * на экран в контексте Fragment.
 */
fun Fragment.showToast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}