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
import com.example.accommodationfinder.ui.mapper.ListingMapper
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

        listingsRecyclerView = findViewById(R.id.listingsRecyclerView)
        val minPriceInput = findViewById<EditText>(R.id.minPriceInput)
        val maxPriceInput = findViewById<EditText>(R.id.maxPriceInput)
        val locationSpinner = findViewById<Spinner>(R.id.locationSpinner)
        val dateInput = findViewById<EditText>(R.id.dateInput)
        val filterBtn = findViewById<Button>(R.id.filterBtn)
        val inboxBtn = findViewById<Button>(R.id.inboxBtn)
        val logoutBtn = findViewById<ImageView>(R.id.logoutBtn)

        adapter = ListingAdapter(this)
        listingsRecyclerView.layoutManager = LinearLayoutManager(this)
        listingsRecyclerView.adapter = adapter

        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            locations
        )
        locationSpinner.adapter = spinnerAdapter

        lifecycleScope.launch {
            db.listingDao().getAllListings().collect { listings ->
                allListings = listings

                val uiListings = listings.map {
                    ListingMapper.toUiModel(this@MainActivity, it)
                }

                adapter.updateListings(uiListings)
            }
        }

        filterBtn.setOnClickListener {
            val minPrice = minPriceInput.text.toString().toDoubleOrNull() ?: 0.0
            val maxPrice = maxPriceInput.text.toString().toDoubleOrNull() ?: Double.MAX_VALUE
            val selectedLocation = locationSpinner.selectedItem.toString()
            val selectedDate = dateInput.text.toString()

            var filtered = allListings

            if (minPrice > 0 || maxPrice < Double.MAX_VALUE) {
                filtered = filtered.filter { it.price in minPrice..maxPrice }
            }

            if (selectedLocation != "All Locations") {
                filtered = filtered.filter {
                    it.location.contains(selectedLocation, ignoreCase = true)
                }
            }

            if (selectedDate.isNotEmpty()) {
                filtered = filtered.filter {
                    it.availabilityDate >= selectedDate
                }
            }

            val uiFiltered = filtered.map {
                ListingMapper.toUiModel(this@MainActivity, it)
            }

            adapter.updateListings(uiFiltered)

            Toast.makeText(
                this,
                "Found ${filtered.size} listings",
                Toast.LENGTH_SHORT
            ).show()
        }

        inboxBtn.setOnClickListener {
            startActivity(Intent(this, InboxActivity::class.java))
        }

        logoutBtn.setOnClickListener {
            SessionManager.logout(this)
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }
    }
}