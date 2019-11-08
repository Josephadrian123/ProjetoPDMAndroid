package com.example.josephadrian.projetopdmandroidgarden

import android.app.Dialog
import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.widget.Toast

class TelaReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "BEM-VINDO!", Toast.LENGTH_LONG).show()
    }
}