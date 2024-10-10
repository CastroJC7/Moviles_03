package com.example.desafio3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio3.databinding.ItemRecursoBinding

class ResourceAdapter(private val resources: List<Materia>) :
    RecyclerView.Adapter<ResourceAdapter.ResourceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
        val binding = ItemRecursoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResourceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
        holder.bind(resources[position])
    }

    override fun getItemCount() = resources.size

    class ResourceViewHolder(private val binding: ItemRecursoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recurso: Materia) {
            binding.titleTextView.text = recurso.title
            binding.descriptionTextView.text = recurso.description
            binding.urlTextView.text = recurso.url
            binding.typeTextView.text = recurso.type
        }
    }
}
