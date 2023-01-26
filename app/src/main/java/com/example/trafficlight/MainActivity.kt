package com.example.trafficlight

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import java.util.*

class MainActivity : Activity() {
    var imageTrafficLight: ImageView? = null
    var counter:Int = 0
    var timer: Timer? = null
    var isRun:Boolean = false
    var imageArray:IntArray = intArrayOf(
        R.drawable.semafor_red,
        R.drawable.semafor_yellow,
        R.drawable.semafor_green)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageTrafficLight = findViewById(R.id.imageTrafficLight)
        imageTrafficLight?.setImageResource(R.drawable.semafor_gray)
    }
    fun onClickStartStop(view: View) {
        view as ImageButton
        if(!isRun) {
            startStop()
            view.setImageResource(R.drawable.button_stop)
            isRun = true
        } else {
            imageTrafficLight?.setImageResource(R.drawable.semafor_gray)
            view.setImageResource(R.drawable.button_start)
            timer?.cancel()
            isRun = false
            counter = 0
        }
    }
    fun startStop(){
        timer = Timer()
        timer?.schedule(object : TimerTask(){
            override fun run() {
                runOnUiThread{
                    imageTrafficLight?.setImageResource(imageArray[counter])
                    counter++
                    if(counter == 3) counter = 0
                }
            }

        }, 0, 1000)
    }
}