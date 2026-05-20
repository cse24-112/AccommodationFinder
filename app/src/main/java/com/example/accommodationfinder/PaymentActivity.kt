package com.example.accommodationfinder

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.appcompat.app.AlertDialog
import com.example.accommodationfinder.data.Reservation
import com.example.accommodationfinder.utils.DateUtils
import com.example.accommodationfinder.utils.SessionManager
import kotlinx.coroutines.launch

class PaymentActivity : AppCompatActivity() {
    private var listingId: Int = -1
    private var depositAmount: Double = 0.0
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        db = AppDatabase.getDatabase(this)
        listingId = intent.getIntExtra("listing_id", -1)
        val listingTitle = intent.getStringExtra("listing_title") ?: ""
        depositAmount = intent.getDoubleExtra("deposit_amount", 0.0)

        val backBtn = findViewById<ImageView>(R.id.backBtn)
        val listingTitleText = findViewById<TextView>(R.id.listingTitle)
        val depositAmountText = findViewById<TextView>(R.id.depositAmount)
        val cardNumberInput = findViewById<EditText>(R.id.cardNumberInput)
        val expiryInput = findViewById<EditText>(R.id.expiryInput)
        val cvcInput = findViewById<EditText>(R.id.cvcInput)
        val termsCheckbox = findViewById<CheckBox>(R.id.termsCheckbox)
        val payBtn = findViewById<Button>(R.id.payBtn)

        listingTitleText.text = listingTitle
        depositAmountText.text = "P ${String.format("%.2f", depositAmount)}"

        backBtn.setOnClickListener {
            finish()
        }

        payBtn.setOnClickListener {
            val cardNumber = cardNumberInput.text.toString()
            val expiry = expiryInput.text.toString()
            val cvc = cvcInput.text.toString()

            if (cardNumber.isEmpty() || expiry.isEmpty() || cvc.isEmpty()) {
                Toast.makeText(this, "Please fill all card details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (cardNumber.length < 12) {
                Toast.makeText(this, "Invalid card number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!termsCheckbox.isChecked) {
                Toast.makeText(this, "Please agree to terms and conditions", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Process payment
            processPayment()
        }
    }

    private fun processPayment() {
        val userId = SessionManager.getUserId(this)
        val referenceNumber = DateUtils.generateReferenceNumber()

        lifecycleScope.launch {
            try {
                // Create reservation
                val reservation = Reservation(
                    userId = userId,
                    listingId = listingId,
                    status = "Completed",
                    paymentAmount = depositAmount,
                    referenceNumber = referenceNumber
                )
                db.reservationDao().insert(reservation)

                // Update listing status to Reserved
                val listing = db.listingDao().getListingById(listingId)
                if (listing != null) {
                    val updatedListing = listing.copy(status = "Reserved")
                    db.listingDao().update(updatedListing)
                }

                // Show success dialog
                AlertDialog.Builder(this@PaymentActivity).apply {
                    setTitle("Payment Successful!")
                    setMessage("Reference Number: $referenceNumber\n\nYour room has been reserved. A confirmation has been sent to your email.")
                    setPositiveButton("Done") { _, _ ->
                        finish()
                    }
                    show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@PaymentActivity, "Payment failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
