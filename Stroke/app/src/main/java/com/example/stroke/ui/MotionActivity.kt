package com.example.stroke.ui

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.widget.TextView
import android.hardware.SensorManager
import android.content.Context
import android.R
import android.content.Intent
import android.speech.tts.TextToSpeech
import android.util.Log
import kotlinx.android.synthetic.main.activity_motion.*
import java.util.*


enum class State{
    DEFAULT,
    LEFT,
    RIGHT
}

class MotionActivity : AppCompatActivity(), SensorEventListener, TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null

    private val lastX: Float = 0.toFloat()
    private val lastY: Float = 0.toFloat()
    private val lastZ: Float = 0.toFloat()

    private var sensorManager: SensorManager? = null
    private var accelerometer: Sensor? = null

    private var deltaXMaxLeft = 0f
    private var deltaYMaxLeft = 0f
    private var deltaZMaxLeft = 0f

    private var deltaXMaxRight = 0f
    private var deltaYMaxRight = 0f
    private var deltaZMaxRight = 0f

    private var deltaX = 0f
    private var deltaY = 0f
    private var deltaZ = 0f

    private var vibrateThreshold = 0f

    var v: Vibrator? = null

    private var state: State = State.DEFAULT


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.stroke.R.layout.activity_motion)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        if (sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            // success! we have an accelerometer

            accelerometer = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            sensorManager!!.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
            vibrateThreshold = accelerometer!!.maximumRange / 2
        } else {
            // fail we dont have an accelerometer!
        }

        btn_reset.setOnClickListener {
            resetValues()

            speakOut("Ține telefonul în mâna stângă pe lângă cord și ridică ușor mâna în față cu palma în sus. Repetă testul cu mâna dreaptă")
        }

        btn_Left.setOnClickListener {
            state = State.LEFT
        }

        btn_Right.setOnClickListener {
            state = State.RIGHT
        }

        btn_stop.setOnClickListener {
            startActivity(Intent(this,CallActivity::class.java))
            finish()
        }

        tts = TextToSpeech(this, this)
    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.ROOT)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!")
            }
        } else {
            Log.e("TTS", "Initilization Failed!")
        }
    }

    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }

    override fun onResume() {
        super.onResume()

        sensorManager?.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    override fun onPause() {
        super.onPause()
        sensorManager?.unregisterListener(this);
    }

    override fun onSensorChanged(event: SensorEvent?) {

        // clean current values
        displayCleanValues();
        // display the current x,y,z accelerometer values
        displayCurrentValues();
        // display the max x,y,z accelerometer values
        displayMaxValues();

        // get the change of the x,y,z values of the accelerometer
        deltaX = Math.abs(lastX - event!!.values[0])
        deltaY = Math.abs(lastY - event!!.values[1])
        deltaZ = Math.abs(lastZ - event!!.values[2])

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    fun resetValues() {
        currentX.setText("0.0")
        currentY.setText("0.0")
        currentZ.setText("0.0")

        maxXRight.setText("0.0")
        maxYRight.setText("0.0")
        maxZRight.setText("0.0")

        maxXLeft.setText("0.0")
        maxYLeft.setText("0.0")
        maxZLeft.setText("0.0")

        deltaXMaxLeft = 0f
        deltaYMaxLeft = 0f
        deltaZMaxLeft = 0f

        deltaXMaxRight = 0f
        deltaYMaxRight = 0f
        deltaZMaxRight = 0f

        state = State.DEFAULT
    }

    fun displayCleanValues() {
        currentX.setText("0.0")
        currentY.setText("0.0")
        currentZ.setText("0.0")
    }

    // display the current x,y,z accelerometer values
    fun displayCurrentValues() {
        currentX.setText(deltaX.toString())
        currentY.setText(deltaY.toString())
        currentZ.setText(deltaZ.toString())
    }

    // display the max x,y,z accelerometer values
    fun displayMaxValues() {

        when(state){
            State.LEFT -> {
                if (deltaX > deltaXMaxLeft) {
                    deltaXMaxLeft = deltaX;
                    maxXLeft?.setText(deltaXMaxLeft.toString());
                }
            }

            State.RIGHT -> {
                if (deltaX > deltaXMaxRight) {
                    deltaXMaxRight = deltaX;
                    maxXRight?.setText(deltaXMaxRight.toString());
                }
            }
        }


        when(state){
            State.LEFT -> {
                if (deltaY > deltaYMaxLeft) {
                    deltaYMaxLeft = deltaY;
                    maxYLeft?.setText(deltaYMaxLeft.toString());
                }
            }

            State.RIGHT -> {
                if (deltaY > deltaYMaxRight) {
                    deltaYMaxRight = deltaY;
                    maxYRight?.setText(deltaYMaxRight.toString());
                }
            }
        }

        when(state){
            State.LEFT -> {
                if (deltaZ > deltaZMaxLeft) {
                    deltaZMaxLeft = deltaZ;
                    maxZLeft?.setText(deltaZMaxLeft.toString());
                }
            }

            State.RIGHT -> {
                if (deltaZ > deltaZMaxRight) {
                    deltaZMaxRight = deltaZ;
                    maxZRight?.setText(deltaZMaxRight.toString());
                }
            }
        }
    }
}
