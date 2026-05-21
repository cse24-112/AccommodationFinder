package com.example.accommodationfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class ListingDetailActivity : AppCompatActivity() {

    private var listingId: Int = -1
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing_detail)

        db = AppDatabase.getDatabase(this)
        listingId = intent.getIntExtra("listing_id", -1)

        if (listingId == -1) {
            Toast.makeText(this, "Invalid listing", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val detailImage = findViewById<ImageView>(R.id.detailImage)
        val detailTitle = findViewById<TextView>(R.id.detailTitle)
        val priceDetail = findViewById<TextView>(R.id.priceDetail)
        val detailLocation = findViewById<TextView>(R.id.detailLocation)
        val detailType = findViewById<TextView>(R.id.detailType)
        val detailDate = findViewById<TextView>(R.id.detailDate)
        val detailDeposit = findViewById<TextView>(R.id.detailDeposit)
        val amenitiesDetail = findViewById<TextView>(R.id.amenitiesDetail)
        val descriptionDetail = findViewById<TextView>(R.id.descriptionDetail)
        val statusBadge = findViewById<TextView>(R.id.statusBadge)
        val chatBtn = findViewById<Button>(R.id.chatBtn)
        val reserveBtn = findViewById<Button>(R.id.reserveBtn)

        lifecycleScope.launch {
            val listing = db.listingDao().getListingById(listingId)

            if (listing != null) {
                val isAvailable = listing.status == "Available"

                detailImage.setImageResource(listing.imageResId)

                detailTitle.text = listing.title
                priceDetail.text = "P ${String.format("%.2f", listing.price)} / month"
                detailLocation.text = listing.location
                detailType.text = listing.type
                detailDate.text = listing.availabilityDate
                detailDeposit.text = "P ${String.format("%.2f", listing.depositAmount)}"
                amenitiesDetail.text = listing.amenities.split(",").joinToString("\n• ", "• ")
                descriptionDetail.text = listing.description

                statusBadge.text = listing.status
                statusBadge.setBackgroundColor(
                    if (isAvailable) {
                        resources.getColor(R.color.primary_color, null)
                    } else {
                        resources.getColor(android.R.color.darker_gray, null)
                    }
                )

                reserveBtn.isEnabled = isAvailable
                reserveBtn.text = if (isAvailable) "Reserve Now" else "Reserved"

                chatBtn.setOnClickListener {
                    val intent = Intent(this@ListingDetailActivity, ChatActivity::class.java).apply {
                        putExtra("listing_id", listingId)
                        putExtra("provider_id", listing.providerId)
                    }
                    startActivity(intent)
                }

                reserveBtn.setOnClickListener {
                    if (isAvailable) {
                        val intent = Intent(this@ListingDetailActivity, PaymentActivity::class.java).apply {
                            putExtra("listing_id", listingId)
                            putExtra("listing_title", listing.title)
                            putExtra("deposit_amount", listing.depositAmount)
                        }
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@ListingDetailActivity,
                            "This room is already reserved",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            } else {
                Toast.makeText(this@ListingDetailActivity, "Listing not found", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}