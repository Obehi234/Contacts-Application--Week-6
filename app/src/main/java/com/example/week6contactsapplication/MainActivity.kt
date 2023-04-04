package com.example.week6contactsapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week6contactsapplication.adapters.ContactsListAdapter
import com.example.week6contactsapplication.adapters.FavoritesListAdapter
import com.example.week6contactsapplication.data.ContactsData
import com.example.week6contactsapplication.data.Favorites
import com.example.week6contactsapplication.model.ContactsModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var rvFavorites: RecyclerView
    private lateinit var rvContacts: RecyclerView
    private lateinit var addContact: FloatingActionButton

    private lateinit var adapter: ContactsListAdapter

    private lateinit var dbReference: DatabaseReference
    private lateinit var dbListener: ValueEventListener
    private val contacts = mutableListOf<ContactsModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvFavorites = findViewById(R.id.rvFavorites)
        rvContacts = findViewById(R.id.rvContacts)
        addContact = findViewById(R.id.fab)

        rvFavoriteImplement()
        rvContactsImplement()

        addContact.setOnClickListener { addNewContact() }
    }

    private fun showData() {
        dbReference = FirebaseDatabase.getInstance().getReference("contacts")
        dbListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                contacts.clear()
                for (contactSnapshot in dataSnapshot.children) {
                    val contact = contactSnapshot.getValue(ContactsModel::class.java)
                    if (contact != null) {
                        contacts.add(contact)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
    }


    private fun rvFavoriteImplement() {
        val favoriteList = mutableListOf(
            Favorites(R.drawable.contact5, "Sewa"),
            Favorites(R.drawable.contact2, "Joia"),
            Favorites(R.drawable.contact1, "Sugar")
        )
        rvFavorites.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvFavorites.setHasFixedSize(true)
        val adapter = FavoritesListAdapter(favoriteList)
        rvFavorites.adapter = adapter
    }

    private fun rvContactsImplement() {
        val contactList = mutableListOf(
            ContactsData(R.drawable.contact1, "Sugar Cookie", "Active", R.drawable.ic_status_offline),
            ContactsData(R.drawable.contact2, "Devi Khan", "Active", R.drawable.ic_status_offline)
        )
        val layoutManager = LinearLayoutManager(this)
        rvContacts.layoutManager = layoutManager
        rvContacts.setHasFixedSize(true)

        adapter = ContactsListAdapter(contactList)
        rvContacts.adapter = adapter

        showData()
    }

    private fun addNewContact() {
        val intent = Intent(this, AddContactActivity::class.java)
        startActivity(intent)
    }
}