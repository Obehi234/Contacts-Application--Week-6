package com.example.week6contactsapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.week6contactsapplication.R
import com.example.week6contactsapplication.data.ContactsData

class ContactsListAdapter(private val contacts: List<ContactsData>) : RecyclerView.Adapter<ContactsListAdapter.ContactsViewHolder>() {
    class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_list_item, parent, false)
        return ContactsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.itemView.findViewById<ImageView>(R.id.ivContact).setImageResource(contacts[position].ivContact)
        holder.itemView.findViewById<ImageView>(R.id.ivStatus).setImageResource(contacts[position].ivStatus)
        holder.itemView.findViewById<TextView>(R.id.tvContact).text = contacts[position].tvContact
        holder.itemView.findViewById<TextView>(R.id.tvStatus).text = contacts[position].tvStatus
    }

    override fun getItemCount(): Int = contacts.size
}