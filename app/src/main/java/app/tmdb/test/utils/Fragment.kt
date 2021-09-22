package app.tmdb.test.utils

import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(directions: NavDirections) {
    findNavController().navigate(directions)
}

fun Fragment.hasPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(
        requireContext(),
        permission
    ) == PackageManager.PERMISSION_GRANTED
}