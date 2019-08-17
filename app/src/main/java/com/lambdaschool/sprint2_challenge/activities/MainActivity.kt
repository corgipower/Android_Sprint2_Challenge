package com.lambdaschool.sprint2_challenge.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.widget.LinearLayoutManager
import com.lambdaschool.sprint2_challenge.data.GroceryItemRepository
import com.lambdaschool.sprint2_challenge.util.GroceryListAdapter
import com.lambdaschool.sprint2_challenge.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GroceryItemRepository.createGrooceryList()

        grocery_list_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = GroceryListAdapter(GroceryItemRepository.groceryList)
        }

        btn_send.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, getSelected())
                type = "text/plain"
            }
            startActivityForResult(intent, 54321)
            createNotification(getSelected())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            if (requestCode == 54321 && resultCode == RESULT_OK) {
                createNotification(getSelected())
            }
        }


    fun getSelected(): String {
        var selectedString = ""
        for (grocery in GroceryItemRepository.groceryList) {
            if (grocery.isSelected) selectedString += "${grocery.itemName}, "
        }

        return selectedString
    }

    fun createNotification(selected: String) {
        val channelId = "${this.packageName}.simplechannel"
        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Shopping List Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val description = "Channel to share the shopping list"

            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description

            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle("List is shared")
                .setContentText(selected)
                .setAutoCancel(true)
        notificationManager.notify(1, notificationBuilder.build())

    }
}
