package app.tmdb.test.ui.home

import android.Manifest
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import app.tmdb.test.databinding.FragmentHomeBinding
import app.tmdb.test.ui.AbsFragment
import app.tmdb.test.utils.Loggable
import app.tmdb.test.utils.hasPermission
import app.tmdb.test.utils.navigate
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : AbsFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate), Loggable {

    private val viewModel: HomeViewModel by viewModels()

    private val locationPermissionCall =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            //recursion
            log("onPermissionResult received")
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!viewModel.isSessionValid() && !viewModel.isGuestSessionValid()) navigate(HomeFragmentDirections.toLogin())
    }


    private fun askLocationPermission() {
        when {
            hasPermission(Manifest.permission.CAMERA) -> {
                takePicture()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                // showDialog and try again
            }
            else -> {
                locationPermissionCall.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun takePicture() {}

    override fun setUp() {

        askLocationPermission()
    }

    override fun setUpObservers() {

    }
}