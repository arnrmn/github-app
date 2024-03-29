package com.arnrmn.githubapp.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.arnrmn.githubapp.R
import com.arnrmn.githubapp.utils.android.BaseFragment
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : BaseFragment() {
    private val args: DetailsFragmentArgs by navArgs()
    override val layoutId = R.layout.fragment_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = args.repository
        activity?.title = repository.name
        openIssuesTextView.text = repository.openIssues.toString()
        closedIssuesTextView.text = repository.closedIssues.toString()
        openPullRequestsTextView.text = repository.openPullRequests.toString()
        closedPullRequestsTextView.text = repository.closedPullRequests.toString()
    }
}