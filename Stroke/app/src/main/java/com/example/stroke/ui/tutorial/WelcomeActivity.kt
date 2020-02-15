package com.example.stroke

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    private var btn: Button? = null
    private var preferenceHelper: PreferenceHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        preferenceHelper = PreferenceHelper(this)

        btn?.setOnClickListener {
            preferenceHelper!!.putIntro("")
            onBackPressed()
        }

    }
}