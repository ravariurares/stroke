package com.example.stroke.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.stroke.R
import kotlinx.android.synthetic.main.activity_call.*

class CallActivity : AppCompatActivity() {

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)


        Handler().postDelayed({
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0752415001"))
                startActivity(intent)
            },
            5000 // value in milliseconds
        )

        btn_call_Now.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0752415001"))
            startActivity(intent)

            startActivity(Intent(this, MapsActivity::class.java))
        }
    }

    override fun onRestart() {
        super.onRestart()

        startActivity(Intent(this, MapsActivity::class.java)) // This method will be executed once the timer is over
        finish()
    }
}
