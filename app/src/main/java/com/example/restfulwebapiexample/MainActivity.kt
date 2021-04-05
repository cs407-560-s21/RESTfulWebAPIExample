package com.example.restfulwebapiexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.koushikdutta.ion.Ion
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun downloadJokeData(view: View) {
        // Download the data from the specified URL
        Ion.with(this)
                .load("https://official-joke-api.appspot.com/random_joke")
                .asString()
                .setCallback { e, result ->
                    // Write code to process the result
                    // e parameter stores the exception if any
                    Log.d(TAG, "The received data : $result")

                    // Helper function to parse/process data
                    parseJokeData(result)
                }
    }


    private fun parseJokeData(result: String){
        // Extract the information from JSON data

        // Received data will be in JSON format as shown below
/*
        {
            "id": 42,
            "type": "general",
            "setup": "If you see a robbery at an Apple Store...",
            "punchline": "Does that make you an iWitness?"
        }
*/

        val data = JSONObject(result)
        val jokeSetup = data.getString("setup")
        val jokePunchline = data.getString("punchline")
        Log.d(TAG, "Joke : $jokeSetup, $jokePunchline")

        // Display the joke
        tv_joke.text = jokeSetup + "\n" +jokePunchline
    }


    fun downloadFunnyImage(view: View) {
        // Download the data from the specified URL
        Ion.with(this)
                .load("https://yesno.wtf/api")
                .asString()
                .setCallback { e, result ->
                    // Write code to process the result
                    // e parameter stores the exception if any
                    Log.d(TAG, "The received data : $result")

                    // Helper function to parse/process data
                    parseFunnyImageData(result)
                }
    }


    private fun parseFunnyImageData(result: String){
        // Extract the information from JSON data

        // Received data will be in JSON format as shown below
/*        {
            "answer": "yes",
            "forced": false,
            "image": "https://yesno.wtf/assets/yes/13-c3082a998e7758be8e582276f35d1336.gif"
}*/
        val data = JSONObject(result)
        val img = data.getString("image")
        val yesOrNo = data.getString("answer")
        //Log.d(TAG, "The image url : $img")

        // Display the image using Ion library, alternatively picasso library can be used
        Ion.with(image).load(img)

        // Display the yes/no text
        tv_yes_no.text = yesOrNo
    }
}