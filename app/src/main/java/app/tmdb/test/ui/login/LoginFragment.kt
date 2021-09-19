package app.tmdb.test.ui.login


import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import app.tmdb.test.databinding.FragmentLoginBinding
import app.tmdb.test.ui.AbsFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : AbsFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

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
    }

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
            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.themoviedb.org/authenticate/$token"))
            try {
                startActivity(intent)
            } catch (ex: ActivityNotFoundException) {
                Snackbar.make(requireView(), "App not found", Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}

// TODO: 9/19/2021 Redirect to app when authentication is granted.