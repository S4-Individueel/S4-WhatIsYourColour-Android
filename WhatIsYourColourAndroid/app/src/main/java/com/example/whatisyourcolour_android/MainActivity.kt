package com.example.whatisyourcolour_android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val colors = arrayOf("Red", "Green", "Blue", "Yellow", "Orange", "Purple")
    private lateinit var colorSpinner: Spinner
    private lateinit var sendButton: Button
    private lateinit var colorAdapter: ArrayAdapter<String>
    private var selectedColor = "Red";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handleIntent(intent)

        colorAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colors)
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        colorSpinner = findViewById(R.id.spinner)
        colorSpinner.adapter = colorAdapter

        sendButton = findViewById(R.id.emit)

        colorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val color = parent.getItemAtPosition(position) as String
                selectedColor = color;
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

//        val receiver: BroadcastReceiver = object : BroadcastReceiver() {
//            override fun onReceive(context: Context, intent: Intent) {
//                val favoriteColor = intent.getStringExtra("favorite_color")
//
//                val text = "Color received: $favoriteColor"
//                val duration = Toast.LENGTH_SHORT
//
//                val toast = Toast.makeText(applicationContext, text, duration)
//                toast.show()
//            }
//        }
//        val filter = IntentFilter()
//        filter.addAction("com.example.intentresolver.favorite_color")
//        registerReceiver(receiver, filter)

        sendButton.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, selectedColor)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, "Share using")
            startActivity(shareIntent)


//            val intent = Intent()
//            intent.action = "com.example.intentresolver.favorite_color"
//            intent.putExtra("favorite_color", selectedColor)
//            sendBroadcast(intent)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        val favoriteColor = intent.getStringExtra(Intent.EXTRA_TEXT)
        // compare the favoriteColor with the user's favorite color
        // if the colors match, play a happy sound, otherwise play a sad sound
    }
}
