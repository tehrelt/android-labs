package ru.evteev.sportapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleOwner
import ru.evteev.la2.R
import ru.evteev.sportapp.fragments.SportCreateFragment
import ru.evteev.sportapp.fragments.SportFragment
import ru.evteev.sportapp.fragments.SportListFragment
import ru.evteev.sportapp.viewmodels.SportsListViewModel


class MainActivity : AppCompatActivity(), SportListFragment.Callback, LifecycleOwner, SportCreateFragment.CreateCallback {

    private lateinit var viewModel: SportsListViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

        viewModel = SportsListViewModel(application);

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flMain, SportListFragment(), "Sport list frag")
            .addToBackStack(null)
            .commit();
    }



    override fun onStart() {
        super.onStart()
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

    override fun createSport(name: String) {
        viewModel.insert(name);
        supportFragmentManager.beginTransaction()
            .replace(R.id.flMain, SportListFragment(), null)
            .commit()
    }
}
