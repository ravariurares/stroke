package com.example.stroke.ui.tutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.stroke.*
import com.github.paolorotolo.appintro.AppIntro

class TutorialActivity : AppIntro() {

    private var preferenceHelper: PreferenceHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState)

        preferenceHelper = PreferenceHelper(this)

        if (preferenceHelper!!.getIntro().equals("no")) {
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            this.finish()
        }

        addSlide(IntroFragment1())  //extend AppIntro and comment setContentView
        addSlide(IntroFragment2())
        addSlide(IntroFragment3())
        addSlide(IntroFragment4())
        addSlide(IntroFragment5())
        addSlide(IntroFragment6())
        addSlide(IntroFragment7())
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        //preferenceHelper!!.putIntro("no")
        this.finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        //preferenceHelper!!.putIntro("no")
        this.finish()
    }

}
