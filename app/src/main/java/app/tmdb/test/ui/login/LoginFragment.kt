package app.tmdb.test.ui.login

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import app.tmdb.test.R
import app.tmdb.test.data.APPROVED
import app.tmdb.test.data.AUTHENTICATION_GRANTED
import app.tmdb.test.data.REQUEST_TOKEN
import app.tmdb.test.databinding.FragmentLoginBinding
import app.tmdb.test.ui.AbsFragment
import app.tmdb.test.utils.Loggable
import app.tmdb.test.utils.navigate
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : AbsFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate), Loggable {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager.setFragmentResultListener(AUTHENTICATION_GRANTED, viewLifecycleOwner) { _, bundle ->
            val wasUserAuthenticated = bundle.getBoolean(APPROVED)

            if (wasUserAuthenticated) {
                binding.progressBar.visibility = View.VISIBLE

                val requestToken = bundle.getString(REQUEST_TOKEN)

                viewModel.setRequestToken(requestToken)
                viewModel.loginWithAccount()
            }
        }
    }

    override fun setUp() {
        with(binding) {
            btnAccountLogin.setOnClickListener {
                progressBar.visibility = View.VISIBLE
                viewModel.getRequestToken()
            }

            btnGuestLogin.setOnClickListener {
                progressBar.visibility = View.VISIBLE
                viewModel.loginAsGuest()
            }
        }
    }

    override fun setUpObservers() {
        viewModel.isSessionActive.observe(viewLifecycleOwner, { isSessionActive ->
            binding.progressBar.visibility = View.GONE
            if (isSessionActive) navigate(LoginFragmentDirections.toHome())
        })

        viewModel.loginError.observe(viewLifecycleOwner, { message ->
            binding.progressBar.visibility = View.GONE
            Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
        })

        viewModel.requestToken.observe(viewLifecycleOwner, { token ->
            binding.progressBar.visibility = View.GONE

            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.themoviedb.org/authenticate/$token?redirect_to=mycustomscheme://")
            )

            try {
                startActivity(intent)
            } catch (ex: ActivityNotFoundException) {
                Snackbar.make(requireView(), R.string.error_app_not_found, Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}