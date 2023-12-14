package ru.evteev.sportapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.evteev.la2.R
import ru.evteev.sportapp.database.DatabaseDescription
import ru.evteev.sportapp.domain.Sport
import ru.evteev.sportapp.fragments.SportFragment
import ru.evteev.sportapp.fragments.SportListFragment


class MainActivity : AppCompatActivity(), SportListFragment.Callback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flMain, SportListFragment(), "Sport list frag")
            .addToBackStack(null)
            .commit();


//        val addButton = findViewById<FloatingActionButton>(R.id.add_button)
//        val refreshButton = findViewById<FloatingActionButton>(R.id.refresh_button)
//
//        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            run {
//                if (result.resultCode == 1) {
//                    val data = result.data
//                    if (data != null) {
//                        val newItem = data.extras?.getString("newSport")
//                        if (newItem != null) {
//                            val contentValues = ContentValues()
//                            contentValues.put(DatabaseDescription.Sport.COLUMN_NAME, newItem)
//                            val itemUri = contentResolver.insert(DatabaseDescription.Sport.CONTENT_URI, contentValues)
//
//                            if(itemUri != null) {
//                                val cursor = contentResolver.query(itemUri, null, null, null, null, null);
//                                if (cursor != null) {
//                                    if (cursor.moveToFirst()) {
//                                        val idIndex = cursor.getColumnIndex(DatabaseDescription.Sport._ID);
//                                        val nameIndex = cursor.getColumnIndex(DatabaseDescription.Sport.COLUMN_NAME);
//
//                                        val id = cursor.getInt(idIndex);
//                                        val name = cursor.getString(nameIndex);
//                                        arrayList.add(Sport(id, name));
//                                        adapter.notifyDataSetChanged()
//                                        sendNotification("${getString(R.string.label_added_new_item)}: $newItem")
//                                    }
//                                    cursor.close()
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        addButton.setOnClickListener {
//            val intent = Intent(this, AddActivity::class.java)
//            launcher.launch(intent)
//        }
//        refreshButton.setOnClickListener {
//            fetch()
//        }
//
//        list.setOnItemClickListener { parent, view, position, id ->
//            val builder = DatabaseDescription.Sport.CONTENT_URI.buildUpon()
//
//            val id = arrayList.elementAt(position).id.toString()
//
//            builder.appendPath(id);
//
//            val uri = builder.build();
//
//
//            contentResolver.delete(uri, null, null)
//            Toast.makeText(this, getString(R.string.sport_deleted) + ": " + arrayList.elementAt(position).name, Toast.LENGTH_SHORT).show();
//            fetch()
//        }

    }

    override fun onStart() {
        super.onStart()
        fetch()
    }

    public override fun onSportSelected(sportId: Int) {
        val fragment = SportFragment.newInstance(sportId);
        supportFragmentManager.beginTransaction()
            .replace(R.id.flMain, fragment)
            .addToBackStack(null)
            .commit()
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

    private fun fetch() {
//        val cursor = contentResolver.query(DatabaseDescription.Sport.CONTENT_URI, null, null, null, null, null)
//
//        if(cursor != null) {
//            arrayList.clear()
//            if (cursor.moveToFirst()) {
//                val idIndex = cursor.getColumnIndex(DatabaseDescription.Sport._ID);
//                val nameIndex = cursor.getColumnIndex(DatabaseDescription.Sport.COLUMN_NAME)
//
//                do {
//                    val id = cursor.getInt(idIndex);
//                    val name = cursor.getString(nameIndex);
//                    arrayList.add(Sport(id, name));
//                } while (cursor.moveToNext())
//            }
//            cursor.close()
//        }
//        adapter.notifyDataSetChanged()
    }
}
