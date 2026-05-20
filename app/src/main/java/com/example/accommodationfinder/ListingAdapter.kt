package com.example.accommodationfinder

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.accommodationfinder.data.Listing

class ListingAdapter(
    private val context: Context,
    private var listings: List<Listing> = emptyList()
) : RecyclerView.Adapter<ListingAdapter.ViewHolder>() {

    fun updateListings(newListings: List<Listing>) {
        listings = newListings
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.listing_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listings[position])
    }

    override fun getItemCount(): Int = listings.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val listingImage: ImageView = itemView.findViewById(R.id.listingImage)
        private val titleText: TextView = itemView.findViewById(R.id.titleText)
        private val locationText: TextView = itemView.findViewById(R.id.locationText)
        private val priceText: TextView = itemView.findViewById(R.id.priceText)
        private val depositText: TextView = itemView.findViewById(R.id.depositText)
        private val amenitiesText: TextView = itemView.findViewById(R.id.amenitiesText)
        private val typeText: TextView = itemView.findViewById(R.id.typeText)
        private val dateText: TextView = itemView.findViewById(R.id.dateText)
        private val statusBadge: TextView = itemView.findViewById(R.id.statusBadge)
        private val viewDetailsBtn: Button = itemView.findViewById(R.id.viewDetailsBtn)

        fun bind(listing: Listing) {

            // IMAGE
            listingImage.setImageResource(listing.imageResId)

            // TEXT DATA
            titleText.text = listing.title
            locationText.text = "📍 ${listing.location}"
            priceText.text = "P ${String.format("%.2f", listing.price)}/month"
            depositText.text = "Deposit: P ${String.format("%.2f", listing.depositAmount)}"
            amenitiesText.text = listing.amenities
            typeText.text = "Type: ${listing.type}"
            dateText.text = "Available: ${listing.availabilityDate}"

            // STATUS
            statusBadge.text = listing.status

            val color = if (listing.status == "Available") {
                ContextCompat.getColor(context, R.color.primary_color)
            } else {
                ContextCompat.getColor(context, android.R.color.darker_gray)
            }

            statusBadge.setBackgroundColor(color)

            // BUTTON CLICK
            viewDetailsBtn.setOnClickListener {
                val intent = Intent(context, ListingDetailActivity::class.java)
                intent.putExtra("listing_id", listing.id)
                context.startActivity(intent)
            }
        }
    }
}