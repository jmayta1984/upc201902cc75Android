package pe.edu.upc.agenda

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_contact.*

class ContactActivity : AppCompatActivity() {

    lateinit var contact: Contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        loadContact()

    }

    private fun loadContact() {
        val gson = Gson()
        val stringObj = intent.getStringExtra("contact")

        contact = gson.fromJson(stringObj, Contact::class.java) ?: Contact(null, "", "")

        if (contact.id != null) {
            etName.setText(contact.name)
            etTelephone.setText(contact.telephone)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.contact_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.save -> {
                saveContact()
                true
            }

            R.id.delete -> {
                deleteContact()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    private fun deleteContact() {
        AppDatabase.getInstance(this).getDao().delete(contact)
        finish()
    }

    private fun saveContact() {
        contact.name = etName.text.toString()
        contact.telephone = etTelephone.text.toString()

        if (contact.id != null) {
            AppDatabase.getInstance(this).getDao().update(contact)
        } else {

            AppDatabase.getInstance(this).getDao().insert(contact)
        }
        finish()
    }
}
