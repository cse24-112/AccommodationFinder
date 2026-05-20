package com.example.accommodationfinder

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.accommodationfinder.data.Message
import com.example.accommodationfinder.utils.SessionManager
import kotlinx.coroutines.launch

class ChatActivity : AppCompatActivity() {
    private var listingId: Int = -1
    private var providerId: Int = -1
    private val userId: Int by lazy { SessionManager.getUserId(this) }
    private lateinit var db: AppDatabase
    private lateinit var messagesAdapter: MessageAdapter
    private lateinit var messagesRecyclerView: RecyclerView
    private lateinit var messageInput: EditText
    private lateinit var sendBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        db = AppDatabase.getDatabase(this)
        listingId = intent.getIntExtra("listing_id", -1)
        providerId = intent.getIntExtra("provider_id", -1)

        val backBtn = findViewById<ImageView>(R.id.backBtn)
        val landlordName = findViewById<TextView>(R.id.landlordName)
        messagesRecyclerView = findViewById(R.id.messagesRecyclerView)
        messageInput = findViewById(R.id.messageInput)
        sendBtn = findViewById(R.id.sendBtn)

        messagesAdapter = MessageAdapter(userId)
        messagesRecyclerView.layoutManager = LinearLayoutManager(this).apply {
            stackFromEnd = true
        }
        messagesRecyclerView.adapter = messagesAdapter

        landlordName.text = "Landlord #$providerId"

        backBtn.setOnClickListener {
            finish()
        }

        // Load messages
        lifecycleScope.launch {
            db.messageDao().getConversation(userId, providerId).collect { messages ->
                messagesAdapter.updateMessages(messages.sortedBy { it.timestamp })
                messagesRecyclerView.scrollToPosition(messagesAdapter.itemCount - 1)
            }
        }

        sendBtn.setOnClickListener {
            val content = messageInput.text.toString().trim()
            if (content.isEmpty()) {
                Toast.makeText(this, "Message cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val message = Message(
                senderId = userId,
                receiverId = providerId,
                listingId = listingId,
                content = content
            )

            lifecycleScope.launch {
                db.messageDao().insert(message)
                messageInput.text.clear()
            }
        }
    }
}
