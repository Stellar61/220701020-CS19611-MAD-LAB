package com.example.exp9


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class SnoozeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val snoozeIntent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, snoozeIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        // Snooze for 10 minutes (600000 milliseconds)
        val snoozeTime = System.currentTimeMillis() + 600000
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, snoozeTime, pendingIntent)

        Toast.makeText(context, "Alarm Snoozed for 10 minutes!", Toast.LENGTH_SHORT).show()
    }
}