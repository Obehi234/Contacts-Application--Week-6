package com.example.week6contactsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.week6contactsapplication.model.ContactsModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddContactActivity : AppCompatActivity() {
    private lateinit var saveContact: TextView
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var phoneNumber: EditText

    private lateinit var dbReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        initViews()
        saveNewContact()
    }

    private fun initViews() {
        saveContact = findViewById(R.id.tvSaveContact)
        saveContact.setOnClickListener { saveNewContact() }
        firstName = findViewById(R.id.etFirstName)
        lastName = findViewById(R.id.etLastName)
        phoneNumber = findViewById(R.id.etPhoneNumber)
        dbReference = FirebaseDatabase.getInstance().getReference("contacts")
    }

    private fun saveData() {
        val fName = firstName.text.toString()
        val surName = lastName.text.toString()
        val phone = phoneNumber.text.toString()

        if(fName.isEmpty()){
            firstName.error = "Please enter contact first name"
        }
        if(surName.isEmpty()){
            lastName.error = "Please enter contact last name"
        }
        if(phone.isEmpty()){
            phoneNumber.error = "Please enter contact phoneNumber"
        }

        val contact = ContactsModel(fName, surName,phone)
        val contactID = dbReference.push().key!!

        dbReference.child(contactID).setValue(contact)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show()

                firstName.text.clear()
                lastName.text.clear()
                phoneNumber.text.clear()

            }.addOnFailureListener {err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()

            }


    }

    private fun saveNewContact() {
        val addContactDialog = AlertDialog.Builder(this)
            .setTitle("Add contact")
            .setMessage("Do you want to add $firstName $lastName to your contacts?")
            .setIcon(R.drawable.ic_new_contact)
            .setPositiveButton("Yes") {_, _ ->
                saveData()
                Toast.makeText(this, "Contact saved successfully", Toast.LENGTH_SHORT).show()

            }
            .setNegativeButton("No") {_, _ ->
                Toast.makeText(this, "Contact not saved", Toast.LENGTH_SHORT).show()

            }.create()

        addContactDialog.show()


    }




}