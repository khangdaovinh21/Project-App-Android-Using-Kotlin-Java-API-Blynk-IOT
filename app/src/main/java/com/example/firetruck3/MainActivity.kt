package com.example.firetruck3

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var textViewSensor1: TextView
    private lateinit var textViewSensor2: TextView
    private lateinit var textViewSensor3: TextView
    private lateinit var textViewSensor4: TextView
    private lateinit var textViewSensor5: TextView
    private lateinit var textViewSensor6: TextView
    private lateinit var connectionSwitch: Switch

    private lateinit var blynkApi: BlynkApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewSensor1 = findViewById(R.id.textViewSensor1)
        textViewSensor2 = findViewById(R.id.textViewSensor2)
        textViewSensor3 = findViewById(R.id.textViewSensor3)
        textViewSensor4 = findViewById(R.id.textViewSensor4)
        textViewSensor5 = findViewById(R.id.textViewSensor5)
        textViewSensor6 = findViewById(R.id.textViewSensor6)
        connectionSwitch = findViewById(R.id.connectionSwitch)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://blynk.cloud/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        blynkApi = retrofit.create(BlynkApiService::class.java)

        connectionSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                connectToESP()
            } else {
                disconnectFromESP()
            }
        }

        // Fetch data for each sensor
        fetchSensorData("V1", textViewSensor1)
        fetchSensorData("V2", textViewSensor2)
        fetchSensorData("V3", textViewSensor3)
        fetchSensorData("V4", textViewSensor4)
        fetchSensorData("V5", textViewSensor5)
        fetchSensorData("V6", textViewSensor6)

        val btnLogout = findViewById<Button>(R.id.buttonLogout)
        btnLogout.setOnClickListener {
            logout()
        }

        val btnBuget = findViewById<Button>(R.id.buttonNew)
        btnBuget.setOnClickListener {
            startActivity(Intent(this, MainActivity6::class.java))
        }
    }

    private fun fetchSensorData(pin: String, textView: TextView) {
        blynkApi.getValueForPin(BlynkConstants.BLYNK_AUTH_TOKEN, pin).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val sensorValue = response.body()?.toIntOrNull() ?: -1
                    textView.setBackgroundColor(if (sensorValue == 0) Color.WHITE else Color.RED)
                    textView.text = sensorValue.toString()
                } else {
                    textView.setBackgroundColor(Color.GRAY)
                    textView.text = "Error: ${response.errorBody()?.string()}"
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                textView.setBackgroundColor(Color.GRAY)
                textView.text = "Failure: ${t.message}"
            }
        })
    }

    private fun connectToESP() {
        // TODO: Implement connection to ESP
    }

    private fun disconnectFromESP() {
        // TODO: Implement disconnection from ESP
    }

    private fun logout() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}
