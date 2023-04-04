package com.example.week6contactsapplication.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.week6contactsapplication.R
import com.example.week6contactsapplication.data.Favorites

class FavoritesListAdapter(private var favorites: List<Favorites>) : RecyclerView.Adapter<FavoritesListAdapter.FavoritesViewHolder>() {
    class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.favorites_list_item, parent, false)
        return FavoritesViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val currentFavorite = favorites[position]
        holder.itemView.findViewById<ImageView>(R.id.card_image).setImageResource(currentFavorite.ivFavorites)
        holder.itemView.findViewById<TextView>(R.id.tvFavorite).text = currentFavorite.tvFavorites
    }

    override fun getItemCount(): Int = favorites.size
}