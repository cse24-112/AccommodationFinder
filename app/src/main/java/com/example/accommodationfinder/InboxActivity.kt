package com.example.accommodationfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.accommodationfinder.utils.SessionManager
import kotlinx.coroutines.launch

class InboxActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var inboxRecyclerView: RecyclerView
    private lateinit var inboxAdapter: InboxAdapter

    private val userId: Int by lazy {
        SessionManager.getUserId(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inbox)

        db = AppDatabase.getDatabase(this)

        inboxRecyclerView = findViewById(R.id.inboxRecyclerView)

        inboxAdapter = InboxAdapter { message ->
            val otherUserId = if (message.senderId == userId) {
                message.receiverId
            } else {
                message.senderId
            }

            val intent = Intent(this, ChatActivity::class.java).apply {
                putExtra("listing_id", message.listingId)
                putExtra("provider_id", otherUserId)
            }

            startActivity(intent)
        }

        inboxRecyclerView.layoutManager = LinearLayoutManager(this)
        inboxRecyclerView.adapter = inboxAdapter

        lifecycleScope.launch {
            db.messageDao().getAllMessages().collect { messages ->
                val inboxMessages = messages
                    .filter { it.senderId == userId || it.receiverId == userId }
                    .distinctBy {
                        if (it.senderId == userId) it.receiverId else it.senderId
                    }

                inboxAdapter.updateMessages(inboxMessages)

                if (inboxMessages.isEmpty()) {
                    Toast.makeText(
                        this@InboxActivity,
                        "No messages yet",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}