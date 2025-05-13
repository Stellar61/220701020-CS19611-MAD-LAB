package com.example.exp9


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Alarm Ringing!", Toast.LENGTH_SHORT).show()

        // Play a simple sound
        val mediaPlayer = MediaPlayer.create(context, R.raw.alarm_sound)
        mediaPlayer.start()

        // Snooze the alarm after 10 minutes
        val snoozeIntent = Intent(context, SnoozeReceiver::class.java)
        context.sendBroadcast(snoozeIntent)
    }
}