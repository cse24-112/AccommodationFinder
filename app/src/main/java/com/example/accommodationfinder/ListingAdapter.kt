package com.example.accommodationfinder

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.accommodationfinder.ui.model.ListingUiModel
class ListingAdapter(
    private val context: Context,
    private var listings: List<ListingUiModel> = emptyList()
) : RecyclerView.Adapter<ListingAdapter.ViewHolder>() {

    fun updateListings(newListings: List<ListingUiModel>) {
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

    override fun getItemCount() = listings.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image = itemView.findViewById<ImageView>(R.id.listingImage)
        private val title = itemView.findViewById<TextView>(R.id.titleText)
        private val location = itemView.findViewById<TextView>(R.id.locationText)
        private val price = itemView.findViewById<TextView>(R.id.priceText)
        private val deposit = itemView.findViewById<TextView>(R.id.depositText)
        private val amenities = itemView.findViewById<TextView>(R.id.amenitiesText)
        private val type = itemView.findViewById<TextView>(R.id.typeText)
        private val date = itemView.findViewById<TextView>(R.id.dateText)
        private val status = itemView.findViewById<TextView>(R.id.statusBadge)
        private val button = itemView.findViewById<Button>(R.id.viewDetailsBtn)

        fun bind(item: ListingUiModel) {

            image.setImageResource(item.imageResId)

            title.text = item.title
            location.text = item.location
            price.text = item.priceText
            deposit.text = item.depositText
            amenities.text = item.amenities
            type.text = item.type
            date.text = item.availabilityDate

            status.text = item.statusText
            status.setBackgroundColor(item.statusColor)

            button.setOnClickListener {
                val intent = Intent(context, ListingDetailActivity::class.java)
                intent.putExtra("listing_id", item.id)
                context.startActivity(intent)
            }
        }
    }
}