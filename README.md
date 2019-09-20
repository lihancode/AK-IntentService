# AK-IntentService
Android kotlin IntentService

# WorkIntentService.kt
```
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
```

# MainActivity.kt
```
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

```



# Log
<img src="https://github.com/lihancode/AK-IntentService/blob/master/log1.png" hieght=800 width=500></img>
<img src="https://github.com/lihancode/AK-IntentService/blob/master/log2.png" hieght=800 width=500></img>
# Result
<img src="https://github.com/lihancode/AK-IntentService/blob/master/img2.png" hieght=600 width=200></img>
<img src="https://github.com/lihancode/AK-IntentService/blob/master/img1.png" hieght=600 width=200></img>

