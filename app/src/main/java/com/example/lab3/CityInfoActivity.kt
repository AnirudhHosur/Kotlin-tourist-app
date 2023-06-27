package com.example.lab3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
Purpose:
    Represents the activity that displays detailed information about a city.
    It receives the city information through Intent extras and passes it to a fragment for display.
Params:
    None
Return:
    None
Functions:
    onCreate: void - Called when the activity is created.
        Retrieves the city information from Intent extras and sets up the fragment for displaying the information.
*/
class CityInfoActivity : AppCompatActivity() {

    companion object {
        const val city_name = "city"                     // Key for the city name extra
        const val city_image = "imageResId"       // Key for the city image resource ID extra
        const val city_continent = "continent"           // Key for the city continent extra
        const val city_language = "language"             // Key for the city language extra
        const val city_population = "population"         // Key for the city population extra
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_info)

        // Retrieve the city information from Intent extras
        val cityName = intent.getStringExtra(city_name)
        val cityImageResId = intent.getIntExtra(city_image, 0)
        val cityContinent = intent.getStringExtra(city_continent)
        val cityLanguage = intent.getStringExtra(city_language)
        val cityPopulation = intent.getStringExtra(city_population)

        // Create a fragment instance and pass the city information as arguments
        val fragment = CityInfoFragment()
        val bundle = Bundle().apply {
            putString(city_name, cityName)
            putInt(city_image, cityImageResId)
            putString(city_continent, cityContinent)
            putString(city_language, cityLanguage)
            putString(city_population, cityPopulation)
        }
        fragment.arguments = bundle

        // Replace the fragment container with the CityInfoFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}