package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.viewmodel.CreationExtras
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private var number: Long = 0
    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById (R.id.edit_value)
        val buttonStart: Button = findViewById (R.id.btn_start)
        val buttonStop: Button = findViewById (R.id.btn_stop)
        val buttonReset: Button = findViewById (R.id.btn_reset)
        val result: TextView = findViewById(R.id.txt_result)

        buttonStart.setOnClickListener {
            try {
                number = editText.text.toString().toLong()

                timer = object : CountDownTimer(number *60*1000,1000) {
                    override fun onTick(millisUntilFinished: Long) {
                       var seconds = millisUntilFinished/1000
                       var minutes = seconds/60
                       seconds = seconds %60
                       result.text = String.format("%02d:%02d", minutes,seconds)
                    }

                    override fun onFinish() {
                       result.text = "o tempo acabou"
                    }
                }

                timer?.start()

            }catch (e:NumberFormatException) {
                Toast.makeText(this, "digite um numero no campo de texto",Toast.LENGTH_SHORT).show()
            }

            buttonStop.setOnClickListener {
                timer?.cancel()

            }

            buttonReset.setOnClickListener {
                number=0
                result.text ="00:00"
                editText.text = null
            }

        }

    }
}