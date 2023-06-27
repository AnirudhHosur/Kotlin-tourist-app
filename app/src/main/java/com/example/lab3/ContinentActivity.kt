package com.example.lab3

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
Purpose:
    Represents the activity that displays a list of cities based on a selected continent.
    Allows the user to click on a city to view detailed information.
Params:
    None
Return:
    None
Functions:
    onCreate: void - Called when the activity is created.
        Sets the layout, retrieves the selected continent, and sets up the city RecyclerView.
    inner class CityAdapter: RecyclerView.Adapter<CityAdapter.CityViewHolder> - Adapter for the city RecyclerView.
        Manages the creation and binding of city views.
    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) - ViewHolder for city views.
        Holds references to the views in each city item and handles click events.
*/
class ContinentActivity : AppCompatActivity() {
    private lateinit var cityRecyclerView: RecyclerView       // RecyclerView for displaying cities
    private lateinit var continent: String                    // Selected continent
    private val cities = listOf(
        // List of cities with their details
        City("Tokyo", R.drawable.tokyo_image, "Asia", "Japanese", "37.4 million"),
        City("Bengaluru", R.drawable.bengaluru, "Asia", "Kannada", "37.4 million"),
        City("Beijing", R.drawable.bejing, "Asia", "Chinese", "37.4 million"),
        City("Auckland", R.drawable.auckland, "Oceania", "English", "37.4 million"),
        City("Barcelona", R.drawable.barca, "Europe", "Spanish", "37.4 million"),
        City("Madrid", R.drawable.madrid, "Europe", "Spanish", "37.4 million"),
        City("Toronto", R.drawable.toronto, "North America", "English", "8.4 million"),
        City("San Jose", R.drawable.sanjose, "North America", "English", "8.4 million"),
        City("Rosario", R.drawable.rosario, "South America", "Spanish", "8.4 million"),
        City("Sao Paulo", R.drawable.saopaulo, "South America", "Spanish", "8.4 million"),
        City("Cairo", R.drawable.cairo, "Africa", "Arabic", "8.4 million"),
        City("Lagos", R.drawable.sanjose, "Africa", "English", "8.4 million")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_continent)

        // Retrieve the selected continent from intent extras
        continent = intent.getStringExtra("continent") ?: ""

        cityRecyclerView = findViewById(R.id.cityRecyclerView)
        val adapter = CityAdapter(cities.filter { it.continent == continent })    // Filter cities by continent
        cityRecyclerView.adapter = adapter
        cityRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    inner class CityAdapter(private val cityList: List<City>) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
            // Inflate the layout for city item view
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
            return CityViewHolder(view)
        }

        override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
            // Bind city data to the view holder
            val city = cityList[position]
            holder.cityNameTextView.text = city.name            // Set city name
            holder.cityImageView.setImageResource(city.imageResId)    // Set city image
        }

        override fun getItemCount(): Int {
            return cityList.size         // Return the number of cities in the list
        }

        // Thanks to this thread in stackoverflow about setting data into intent
        // https://stackoverflow.com/questions/5265913/how-to-use-putextra-and-getextra-for-string-data
        inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val cityNameTextView: TextView = itemView.findViewById(R.id.cityNameTextView)     // City name TextView
            val cityImageView: ImageView = itemView.findViewById(R.id.cityImageView)           // City image ImageView

            init {
                itemView.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val selectedCity = cityList[position]

                        // Create an intent to navigate to CityInfoActivity
                        val intent = Intent(itemView.context, CityInfoActivity::class.java)
                        intent.putExtra("city", selectedCity.name)                      // Pass selected city name
                        intent.putExtra("continent", selectedCity.continent)            // Pass selected city's continent
                        intent.putExtra("language", selectedCity.language)              // Pass selected city's language
                        intent.putExtra("population", selectedCity.population)          // Pass selected city's population
                        intent.putExtra("imageResId", selectedCity.imageResId)          // Pass selected city's image resource ID
                        itemView.context.startActivity(intent)                         // Start CityInfoActivity
                    }
                }
            }
        }
    }
}

