package com.arnrmn.githubapp.repositories

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arnrmn.usecase.repositories.Repository
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryViewHolder(
    private val view: View,
    private val listener: (Repository) -> Unit
) : RecyclerView.ViewHolder(view) {

    fun bind(repository: Repository) {
        view.titleTextView.text = repository.name
        view.urlTextView.text = repository.url
        view.setOnClickListener { listener.invoke(repository) }
    }
}