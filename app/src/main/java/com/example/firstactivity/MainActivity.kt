package com.example.firstactivity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import com.example.firstactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    var name: String = ""
    var email: String = ""
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var preference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preference = getSharedPreferences(NAME_OF_XML_FILE, MODE_PRIVATE)

        Log.e("Josh", "NAME was saved as ${preference.getString(NAME_KEY,"")}")
        Log.e("Josh", "EMAIL was saved as ${preference.getString(EMAIL_KEY,"")}")

        if (preference.getString(NAME_KEY, "").toString().isNotBlank() &&
            preference.getString(EMAIL_KEY,"").toString().isNotBlank()
        ) {
            startSecondActivity()
        }

        //boiler plate
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Click listeners
        binding.btnSave.setOnClickListener {
            saveOurData()
            startSecondActivity()
        }
    }

    private fun startSecondActivity() {
        val intent = Intent(applicationContext, SecondActivity::class.java)
        startActivity(intent)
    }

    private fun saveOurData() {
        //get a reference to the SharedPreferences.Editor that is responsible
        //for making changes to the preferences
        val editor = preference.edit()


        //use with(binding) to make multiple adjustments to binding without having
        //to repeat it. ex. binding.txtName.txt

        with(binding) {
            name = etName.text.toString()
            email = etEmail.text.toString()
        }

        //add data to the editor with the put method,
        // remove data with the remove(<key>) method
        editor.putString(NAME_KEY, name)
        editor.putString(EMAIL_KEY, email)

        //request the changes be executed
        editor.apply()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}