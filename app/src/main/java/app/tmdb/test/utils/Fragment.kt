package app.tmdb.test.utils

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


fun Fragment.navigate(resId: Int) {
    findNavController().navigate(resId)
}