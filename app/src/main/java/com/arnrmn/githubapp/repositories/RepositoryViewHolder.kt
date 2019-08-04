package com.arnrmn.githubapp.repositories

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arnrmn.usecase.repositories.Repository
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryViewHolder(
    override val containerView: View,
    private val listener: (Repository) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(repository: Repository) {
        containerView.titleTextView.text = repository.name
        containerView.urlTextView.text = repository.url
        containerView.setOnClickListener { listener.invoke(repository) }
    }
}