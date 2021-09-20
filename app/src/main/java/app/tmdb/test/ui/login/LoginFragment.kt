package app.tmdb.test.ui.login

import android.Manifest
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import app.tmdb.test.databinding.FragmentLoginBinding
import app.tmdb.test.ui.AbsFragment
import app.tmdb.test.utils.Loggable
import app.tmdb.test.utils.hasPermission
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : AbsFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate), Loggable {

    private val viewModel: LoginViewModel by viewModels()

    private val locationPermissionCall =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            //recursion
            log("onPermissionResult received")
        }

    override fun setUp() {
        with(binding) {
            btnAccountLogin.setOnClickListener {
                progressBar.visibility = View.VISIBLE
                viewModel.loginWithAccount()
            }

            btnGuestLogin.setOnClickListener {
                progressBar.visibility = View.VISIBLE
                viewModel.loginAsGuest()
            }
        }

        askLocationPermission()
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

    @SuppressLint("QueryPermissionsNeeded")
    override fun setUpObservers() {
        viewModel.isSessionActive.observe(viewLifecycleOwner, {
            binding.progressBar.visibility = View.GONE
            if (it) findNavController().popBackStack()
        })

        viewModel.loginError.observe(viewLifecycleOwner, {
            binding.progressBar.visibility = View.GONE
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        })

        viewModel.requestToken.observe(viewLifecycleOwner, { token ->
            binding.progressBar.visibility = View.GONE
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.themoviedb.org/authenticate/$token")
            )
            try {
                startActivity(intent)
            } catch (ex: ActivityNotFoundException) {
                Snackbar.make(requireView(), "App not found", Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}

// TODO: 9/19/2021 Redirect to app when authentication is granted.