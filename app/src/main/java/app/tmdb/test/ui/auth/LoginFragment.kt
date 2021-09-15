package app.tmdb.test.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.tmdb.test.databinding.FragmentLoginBinding
import app.tmdb.test.ui.AbsFragment

class LoginFragment : AbsFragment<FragmentLoginBinding>() {

    private lateinit var viewModel: Any

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initBinding() {
        with(binding) {

        }
    }
}