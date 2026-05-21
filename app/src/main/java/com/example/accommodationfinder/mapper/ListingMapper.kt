package com.example.accommodationfinder.ui.mapper

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.accommodationfinder.R
import com.example.accommodationfinder.data.Listing
import com.example.accommodationfinder.ui.model.ListingUiModel

object ListingMapper {

    fun toUiModel(
        context: Context,
        listing: Listing
    ): ListingUiModel {

        val isAvailable = listing.status == "Available"

        return ListingUiModel(
            id = listing.id,

            title = listing.title,

            location = "📍 ${listing.location}",

            priceText = "P ${listing.price}/month",

            depositText = "Deposit: P ${listing.depositAmount}",

            amenities = listing.amenities,

            type = "Type: ${listing.type}",

            availabilityDate = "Available: ${listing.availabilityDate}",

            imageResId = listing.imageResId,

            statusText =
                if (isAvailable) "Available"
                else "Reserved",

            statusColor =
                if (isAvailable)
                    ContextCompat.getColor(
                        context,
                        R.color.primary_color
                    )
                else
                    ContextCompat.getColor(
                        context,
                        android.R.color.darker_gray
                    )
        )
    }
}