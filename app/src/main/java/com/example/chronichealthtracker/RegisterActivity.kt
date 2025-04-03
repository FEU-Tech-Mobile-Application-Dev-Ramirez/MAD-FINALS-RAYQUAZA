package com.example.chronichealthtracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.chronichealthtracker.data.UserDatabaseHelper
class RegisterActivity : ComponentActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var haveAccountTextView: TextView
    private lateinit var dbHelper: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        usernameEditText = findViewById(R.id.et_username)
        passwordEditText = findViewById(R.id.et_password)
        registerButton = findViewById(R.id.btn_register)
        haveAccountTextView = findViewById(R.id.tv_login)
        dbHelper = UserDatabaseHelper(this)


        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                dbHelper.addUser(username, password)
                Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show()
            }
        }


        haveAccountTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
