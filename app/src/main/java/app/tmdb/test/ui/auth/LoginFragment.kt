package app.tmdb.test.ui.auth

import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import app.tmdb.test.R
import app.tmdb.test.databinding.FragmentLoginBinding
import app.tmdb.test.ui.AbsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : AbsFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun setUp() {
        binding.setUpApiKeyAutoComplete()
    }

    override fun setUpObservers() {
        TODO("Not yet implemented")
    }

    private fun FragmentLoginBinding.setUpApiKeyAutoComplete() {
        val adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            resources.getStringArray(R.array.api_keys))

        apiKeyAutocomplete.setAdapter(adapter)
        apiKeyAutocomplete.setOnItemClickListener { _, _, position, _ ->
            // Do some stuff
        }
    }
}