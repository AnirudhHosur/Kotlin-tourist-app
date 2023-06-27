package com.example.lab3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
Purpose:
    Represents a fragment that displays detailed information about a city.
    It receives the city information as arguments and populates the views accordingly.
Params:
    None
Return:
    None
Functions:
    onCreate: void - Called when the fragment is created.
        Retrieves the city information from arguments.
    onCreateView: View? - Called when the fragment's view is being created.
        Inflates the layout and sets up the views with the city information.
*/
class CityInfoFragment : Fragment() {
    private lateinit var cityName: String                // Stores the city name
    private var cityImageResId: Int = 0                  // Stores the city image resource ID
    private lateinit var cityContinent: String           // Stores the city continent
    private lateinit var cityLanguage: String            // Stores the city language
    private lateinit var cityPopulation: String          // Stores the city population

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // Retrieve the city information from arguments
            cityName = it.getString(CityInfoActivity.city_name, "")
            cityImageResId = it.getInt(CityInfoActivity.city_image, 0)
            cityContinent = it.getString(CityInfoActivity.city_continent, "")
            cityLanguage = it.getString(CityInfoActivity.city_language, "")
            cityPopulation = it.getString(CityInfoActivity.city_population, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_city_info_fragment, container, false)

        // Get references to the views in the layout
        val cityNameTextView = view.findViewById<TextView>(R.id.cityNameTextView)
        val cityImageView = view.findViewById<ImageView>(R.id.cityImageView)
        val populationTextView = view.findViewById<TextView>(R.id.populationTextView)
        val languageTextView = view.findViewById<TextView>(R.id.languageTextView)

        // Set the city information in the views
        cityNameTextView.text = cityName
        cityImageView.setImageResource(cityImageResId)
        populationTextView.text = "Population: $cityPopulation"
        languageTextView.text = "Language Spoken: $cityLanguage"

        return view
    }
}