package com.example.accommodationfinder

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.accommodationfinder.data.Listing
import com.example.accommodationfinder.utils.DateUtils
import com.example.accommodationfinder.utils.SessionManager
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var listingsRecyclerView: RecyclerView
    private lateinit var adapter: ListingAdapter
    private lateinit var db: AppDatabase
    private var allListings = listOf<Listing>()

    private val locations = listOf(
        "All Locations",
        "Gaborone",
        "Tlokweng",
        "Mogoditshane",
        "Phase 2",
        "Block 6",
        "UB Area"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AppDatabase.getDatabase(this)

        // Initialize views
        listingsRecyclerView = findViewById(R.id.listingsRecyclerView)
        val minPriceInput = findViewById<EditText>(R.id.minPriceInput)
        val maxPriceInput = findViewById<EditText>(R.id.maxPriceInput)
        val locationSpinner = findViewById<Spinner>(R.id.locationSpinner)
        val dateInput = findViewById<EditText>(R.id.dateInput)
        val filterBtn = findViewById<Button>(R.id.filterBtn)
        val logoutBtn = findViewById<ImageView>(R.id.logoutBtn)

        // Setup RecyclerView
        adapter = ListingAdapter(this)
        listingsRecyclerView.layoutManager = LinearLayoutManager(this)
        listingsRecyclerView.adapter = adapter

        // Setup location spinner
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, locations)
        locationSpinner.adapter = spinnerAdapter

        // Load all listings
        lifecycleScope.launch {
            db.listingDao().getAllListings().collect { listings ->
                allListings = listings
                adapter.updateListings(listings)
            }
        }

        // Filter button listener
        filterBtn.setOnClickListener {
            val minPrice = minPriceInput.text.toString().toDoubleOrNull() ?: 0.0
            val maxPrice = maxPriceInput.text.toString().toDoubleOrNull() ?: Double.MAX_VALUE
            val selectedLocation = locationSpinner.selectedItem.toString()
            val selectedDate = dateInput.text.toString()

            var filtered = allListings

            // Filter by price
            if (minPrice > 0 || maxPrice < Double.MAX_VALUE) {
                filtered = filtered.filter { it.price in minPrice..maxPrice }
            }

            // Filter by location
            if (selectedLocation != "All Locations") {
                filtered = filtered.filter { it.location.contains(selectedLocation, ignoreCase = true) }
            }

            // Filter by date
            if (selectedDate.isNotEmpty()) {
                filtered = filtered.filter { it.availabilityDate >= selectedDate }
            }

            adapter.updateListings(filtered)
            Toast.makeText(this, "Found ${filtered.size} listings", Toast.LENGTH_SHORT).show()
        }

        // Logout button listener
        logoutBtn.setOnClickListener {
            SessionManager.logout(this)
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }
    }
}
