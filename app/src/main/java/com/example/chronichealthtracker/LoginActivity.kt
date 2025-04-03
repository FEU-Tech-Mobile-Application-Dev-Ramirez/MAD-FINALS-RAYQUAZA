package com.example.chronichealthtracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.chronichealthtracker.data.UserDatabaseHelper

class LoginActivity : ComponentActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var dbHelper: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        usernameEditText = findViewById(R.id.et_username)
        passwordEditText = findViewById(R.id.et_password)
        loginButton = findViewById(R.id.btn_login) // Initialize loginButton
        registerButton = findViewById(R.id.btn_register)
        dbHelper = UserDatabaseHelper(this)


        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (dbHelper.checkUser(username, password)) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }


        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
