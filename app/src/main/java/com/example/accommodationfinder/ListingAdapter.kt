package com.example.accommodationfinder

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.accommodationfinder.data.Listing

class ListingAdapter(
    private val context: Context,
    private var listings: List<Listing> = emptyList()
) : RecyclerView.Adapter<ListingAdapter.ViewHolder>() {

    fun updateListings(newListings: List<Listing>) {
        this.listings = newListings
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listing_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listing = listings[position]
        holder.bind(listing)
    }

    override fun getItemCount(): Int = listings.size

    inner class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        private val titleText = itemView.findViewById<TextView>(R.id.titleText)
        private val locationText = itemView.findViewById<TextView>(R.id.locationText)
        private val priceText = itemView.findViewById<TextView>(R.id.priceText)
        private val depositText = itemView.findViewById<TextView>(R.id.depositText)
        private val amenitiesText = itemView.findViewById<TextView>(R.id.amenitiesText)
        private val typeText = itemView.findViewById<TextView>(R.id.typeText)
        private val dateText = itemView.findViewById<TextView>(R.id.dateText)
        private val statusBadge = itemView.findViewById<TextView>(R.id.statusBadge)
        private val viewDetailsBtn = itemView.findViewById<Button>(R.id.viewDetailsBtn)

        fun bind(listing: Listing) {
            titleText.text = listing.title
            locationText.text = listing.location
            priceText.text = "P ${String.format("%.2f", listing.price)}/month"
            depositText.text = "Deposit: P ${String.format("%.2f", listing.depositAmount)}"
            amenitiesText.text = listing.amenities
            typeText.text = "Type: ${listing.type}"
            dateText.text = "Available: ${listing.availabilityDate}"
            statusBadge.text = listing.status
            statusBadge.setBackgroundColor(
                if (listing.status == "Available") {
                    context.resources.getColor(R.color.primary_color, null)
                } else {
                    context.resources.getColor(android.R.color.darker_gray, null)
                }
            )

            viewDetailsBtn.setOnClickListener {
                val intent = Intent(context, ListingDetailActivity::class.java).apply {
                    putExtra("listing_id", listing.id)
                }
                context.startActivity(intent)
            }
        }
    }
}
