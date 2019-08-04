package com.arnrmn.githubapp.repositories

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.arnrmn.githubapp.R
import com.arnrmn.githubapp.utils.android.BaseFragment
import kotlinx.android.synthetic.main.fragment_repositories.*

class RepositoriesFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_repositories

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openDetailsButton.setOnClickListener {
            val action = RepositoriesFragmentDirections.actionRepositoriesFragmentToDetailsFragment("test")
            findNavController().navigate(action)
        }
    }
}