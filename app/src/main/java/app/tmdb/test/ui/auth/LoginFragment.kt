package app.tmdb.test.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import app.tmdb.test.databinding.FragmentLoginBinding
import app.tmdb.test.ui.AbsFragment

class LoginFragment : AbsFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {
        TODO("Not yet implemented")
    }

    override fun setUpObservers() {
        TODO("Not yet implemented")
    }
}