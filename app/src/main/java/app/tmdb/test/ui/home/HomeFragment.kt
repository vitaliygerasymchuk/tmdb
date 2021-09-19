package app.tmdb.test.ui.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import app.tmdb.test.R
import app.tmdb.test.databinding.FragmentHomeBinding
import app.tmdb.test.ui.AbsFragment
import app.tmdb.test.utils.navigate
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : AbsFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!viewModel.isSessionValid()) {
            navigate(R.id.loginFragment)
        }
    }

    override fun setUp() {

    }

    override fun setUpObservers() {

    }
}