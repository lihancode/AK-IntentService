package com.lihan.ak_intentservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    enum class Task{
        IMAGE_DOWNLOAD,
        DATA_SEARCH,
        DOWNLOAD_DATA
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intentServiceButton.setOnClickListener {

            val intent = Intent(this@MainActivity,WorkIntentService::class.java)
            startService(intent.apply {
                putExtra("TASK",Task.IMAGE_DOWNLOAD.name)
            })
            startService(intent.apply {
                putExtra("TASK",Task.DATA_SEARCH.name)
            })
            startService(intent.apply {
                putExtra("TASK",Task.DOWNLOAD_DATA.name)
            })
        }

        registerReceiver(object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                resultTextView.append("${intent?.action} done \n")
            }

          }, IntentFilter().apply {
            for(Task in Task.values()){
                addAction(Task.name)
            }
          }
        )


    }
}
