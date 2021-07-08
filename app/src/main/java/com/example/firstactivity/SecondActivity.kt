package com.example.firstactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstactivity.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private var _binding:ActivitySecondBinding? = null
    private val binding:ActivitySecondBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //BP
        _binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val pref = getSharedPreferences(NAME_OF_XML_FILE, MODE_PRIVATE)

        //use with(binding) as explained in MainActivity
        with(binding){
            txtName.text = pref.getString(NAME_KEY,"SHOULDA BEEN NAME")
            txtEmail.text = pref.getString(EMAIL_KEY,"shouLDA BEEN EMAIL")

            btnClear.setOnClickListener {
                // access editor, tell the editor to clear data and apply the changes
                pref.edit().clear().apply()
                txtEmail.text = ""
                txtName.text = ""
            }
            //close activity when clicked
            btnBack.setOnClickListener { this@SecondActivity.finish() }
        }
    }

    override fun onDestroy() {
        //empty reference.
        _binding= null
        super.onDestroy()
    }
}