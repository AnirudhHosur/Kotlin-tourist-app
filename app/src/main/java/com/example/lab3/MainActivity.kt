package com.example.lab3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var continentListView: ListView     // ListView to display the list of continents
    private val continents = arrayOf("Asia", "Europe", "North America", "South America", "Africa", "Oceania")    // Array of continent names

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        continentListView = findViewById(R.id.continentListView)    // Reference to the ListView in the layout

        // Create an ArrayAdapter to populate the ListView with continent names
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, continents)

        // Set the adapter for the ListView
        continentListView.adapter = adapter

        // Set an item click listener for the ListView
        continentListView.setOnItemClickListener { _, _, position, _ ->
            val selectedContinent = continents[position]    // Get the selected continent from the clicked position
            val intent = Intent(this, ContinentActivity::class.java)
            intent.putExtra("continent", selectedContinent)    // Pass the selected continent name to the ContinentActivity
            startActivity(intent)    // Start the ContinentActivity
        }
    }
}