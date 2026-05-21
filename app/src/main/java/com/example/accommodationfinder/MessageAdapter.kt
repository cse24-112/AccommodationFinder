package com.example.accommodationfinder

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.accommodationfinder.data.Message
import com.google.android.material.card.MaterialCardView

class MessageAdapter(
    private val currentUserId: Int
) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    private var messages: List<Message> = emptyList()

    fun updateMessages(newMessages: List<Message>) {
        messages = newMessages
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_item, parent, false)

        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount(): Int = messages.size

    inner class MessageViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val card =
            itemView.findViewById<MaterialCardView>(R.id.messageCard)

        private val messageText =
            itemView.findViewById<TextView>(R.id.messageText)

        private val container =
            itemView.findViewById<FrameLayout>(R.id.messageContainer)

        fun bind(message: Message) {
            messageText.text = message.content

            val isFromCurrentUser = message.senderId == currentUserId

            val layoutParams = card.layoutParams as FrameLayout.LayoutParams

            if (isFromCurrentUser) {
                layoutParams.gravity = Gravity.END
                layoutParams.marginStart = dpToPx(80)
                layoutParams.marginEnd = 0

                card.setCardBackgroundColor(
                    ContextCompat.getColor(itemView.context, R.color.primary_color)
                )
                messageText.setTextColor(Color.WHITE)

            } else {
                layoutParams.gravity = Gravity.START
                layoutParams.marginStart = 0
                layoutParams.marginEnd = dpToPx(80)

                card.setCardBackgroundColor(
                    ContextCompat.getColor(itemView.context, R.color.lighter_gray)
                )
                messageText.setTextColor(Color.BLACK)
            }

            card.layoutParams = layoutParams
        }

        private fun dpToPx(dp: Int): Int {
            return (dp * itemView.context.resources.displayMetrics.density).toInt()
        }
    }
}