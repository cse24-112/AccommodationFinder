package com.example.accommodationfinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.accommodationfinder.data.Message
import com.google.android.material.card.MaterialCardView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class InboxAdapter(
    private val onChatClick: (Message) -> Unit
) : RecyclerView.Adapter<InboxAdapter.InboxViewHolder>() {

    private var messages: List<Message> = emptyList()

    fun updateMessages(newMessages: List<Message>) {
        messages = newMessages
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InboxViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.inbox_item, parent, false)

        return InboxViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: InboxViewHolder,
        position: Int
    ) {

        holder.bind(messages[position])
    }

    override fun getItemCount(): Int = messages.size

    inner class InboxViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val card =
            itemView.findViewById<MaterialCardView>(R.id.inboxCard)

        private val userName =
            itemView.findViewById<TextView>(R.id.userName)

        private val lastMessage =
            itemView.findViewById<TextView>(R.id.lastMessage)

        private val messageTime =
            itemView.findViewById<TextView>(R.id.messageTime)

        fun bind(message: Message) {

            userName.text = "User #${message.senderId}"

            lastMessage.text = message.content

            val formattedTime = SimpleDateFormat(
                "HH:mm",
                Locale.getDefault()
            ).format(Date(message.timestamp))

            messageTime.text = formattedTime

            card.setOnClickListener {
                onChatClick(message)
            }
        }
    }
}