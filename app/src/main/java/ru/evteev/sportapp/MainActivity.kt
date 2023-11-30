package ru.evteev.sportapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.evteev.la2.R
import ru.evteev.sportapp.database.DatabaseHelper
import ru.evteev.sportapp.database.SportContentProvider


class MainActivity : ComponentActivity() {

    private lateinit var sportContentProvider: SportContentProvider
    private lateinit var arrayList: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

        sportContentProvider = SportContentProvider();

        arrayList = ArrayList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)

        val addButton = findViewById<FloatingActionButton>(R.id.add_button)

        val list = findViewById<ListView>(R.id.list_view)
        list.adapter = adapter

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            run {
                if (result.resultCode == 1) {
                    val data = result.data
                    if (data != null) {
                        val newItem = data.extras?.getString("newSport")
                        if (newItem != null) {
                            arrayList.add(newItem)
                            adapter.notifyDataSetChanged()
                            sendNotification("${getString(R.string.label_added_new_item)}: $newItem")
                        }
                    }
                }
            }
        }

        addButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            launcher.launch(intent)
        }
    }

    private fun sendNotification(text: String) {
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "1337"
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel (
                channelId,
                "Sport List App",
                NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        manager.notify(1, notificationBuilder.build())
    }
}
