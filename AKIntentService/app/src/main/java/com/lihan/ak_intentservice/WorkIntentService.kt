package com.lihan.ak_intentservice

import android.app.IntentService
import android.content.Intent
import android.util.Log

class WorkIntentService  : IntentService(WorkIntentService::class.java.simpleName){

    private val TAG = WorkIntentService::class.java.simpleName

    override fun onHandleIntent(intent: Intent?) {
        val taskString = intent?.getStringExtra("TASK")!!
        Log.d(TAG, "onHandleIntent: Action is  $taskString")
        for (i in 0..5){
            Thread.sleep(1000)
            Log.d(TAG, "waiting is $i ")
        }
        sendTaskDoneBroadcast(taskString)

    }
    private fun sendTaskDoneBroadcast(task : String){
        sendBroadcast(Intent().apply {
            action = task
        })
    }



}