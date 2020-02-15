package com.example.stroke.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.stroke.R
import com.example.stroke.ui.MotionActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val btnMotion: Button = root.findViewById(R.id.btn_motion)
        btnMotion.setOnClickListener {
            startActivity(Intent(context, MotionActivity::class.java))
        }

        val btnDexterity: Button = root.findViewById(R.id.btn_dexterity)
        btnMotion.setOnClickListener {
            startActivity(Intent(context, MotionActivity::class.java))
        }

        val btnQuestion: Button = root.findViewById(R.id.btn_question)
        btnMotion.setOnClickListener {
            startActivity(Intent(context, MotionActivity::class.java))
        }

        return root
    }
}