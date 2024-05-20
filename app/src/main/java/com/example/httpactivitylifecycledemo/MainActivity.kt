package com.example.httpactivitylifecycledemo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "onCreate")

        val fetchButton: Button = findViewById(R.id.fetchButton)
        fetchButton.setOnClickListener {
            // When the button is clicked, fetch user data
            fetchUserData()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy")
    }

    private fun fetchUserData() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // Call the fetchUsers function from FetchDataTask to fetch the list of users
                val userList = FetchDataTask.fetchUsers()
                // Update UI with the fetched list of users
                updateUI(userList)
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error
            }
        }
    }

    private fun updateUI(userList: List<UserItem>) {
        val usersTextView: TextView = findViewById(R.id.usersTextView)
        // Clear existing text
        usersTextView.text = ""

        // Append each user's information to the text view
        userList.forEach { user ->
            userList.forEachIndexed { index, user ->
                // Append user details with a divider between users
                usersTextView.append(
                    """
            User ${index + 1}:
            User ID: ${user.id}
            Name: ${user.name}
            Email: ${user.email}
            Phone: ${user.phone}
            Address: ${user.address.street}, ${user.address.city}, ${user.address.state}, ${user.address.zip}
            Age: ${user.age}
            
            """.trimIndent()
                )

                // Add a divider between users
                if (index < userList.size - 1) {
                    usersTextView.append("\n--------------------------------------------\n\n")
                }
            }
        }
    }
}
