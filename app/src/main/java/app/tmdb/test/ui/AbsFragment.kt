package app.tmdb.test.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class AbsFragment<V : ViewBinding>(private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> V) : Fragment() {

    private var _binding: V? = null
    protected val binding: V get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflater(inflater, container, false)
        setUp()
        setUpObservers()
        return binding.root
    }

    abstract fun setUp()

    abstract fun setUpObservers()

}