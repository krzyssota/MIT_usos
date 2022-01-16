package com.example.mit_usos

import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.mit_usos.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_department.*
import kotlinx.android.synthetic.main.activity_department.view.*
import android.view.ScaleGestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener





class Department : AppCompatActivity() {
    private var currentImage = R.drawable.class4420
    private var currentFloor = "III piętro"
    private var department = true
    private var scaleGestureDetector: ScaleGestureDetector? = null
    private var mScaleFactor = 1.0f
    private var departmentImage: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_department)

        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener())

        departmentImage = findViewById<ImageView>(R.id.department_image)
        val floorView = findViewById<TextView>(R.id.floor_view)
        val changeViewButton = findViewById<Button>(R.id.change_view_button)

        val classNum = intent.extras?.getInt("class")
        when (classNum) {
            4420 -> {
                departmentImage?.setImageResource(R.drawable.class4420)
                currentImage = R.drawable.class4420
                floorView.text = "III piętro"
            }
            else -> {
                departmentImage?.setImageResource(R.drawable.class5840)
                currentImage = R.drawable.class5840
                floorView.text = "IV piętro"
            }
        }

        currentFloor = floorView.text as String

        changeViewButton.setOnClickListener {
            if (department) {
                departmentImage?.setImageResource(R.drawable.class_view)
                floorView.text = "Sala " + classNum
                changeViewButton.text = "Widok wydziału"
                department = false
            } else {
                departmentImage?.setImageResource(currentImage)
                floorView.text = currentFloor
                changeViewButton.text = "Widok sali"
                department = true
            }
        }

        val help = findViewById(R.id.fab) as FloatingActionButton
        help.setOnClickListener{view ->
            showDialog(this@Department, classNum)
        }
    }

    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        scaleGestureDetector!!.onTouchEvent(motionEvent)
        return true
    }
    private inner class ScaleListener : SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            mScaleFactor *= scaleGestureDetector.scaleFactor
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f))
            departmentImage!!.scaleX = mScaleFactor
            departmentImage!!.scaleY = mScaleFactor
            return true
        }
    }

    fun showDialog(activity: Activity?, classNum: Int?) {
        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.help_dialog)

        val access2 = dialog.findViewById(R.id.access2) as TextView
        when (classNum) {
            4420 -> {
                access2.setText("Szerokie drzwi");
            }
        }


        val callButton = dialog.findViewById(R.id.call_button) as Button
        callButton.setOnClickListener {
            val number = "225524222"
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$number")
            startActivity(intent)
        }
        val mailButton = dialog.findViewById(R.id.mail_button) as Button;
        mailButton.setOnClickListener {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "message/rfc822"
            i.putExtra(Intent.EXTRA_EMAIL, arrayOf("bon@uw.edu.pl"))
            i.putExtra(Intent.EXTRA_SUBJECT, "Problem z dostępnościa")
            try {
                startActivity(Intent.createChooser(i, "Send mail..."))
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(
                    this@Department,
                    "There are no email clients installed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        dialog.show()
        val window: Window? = dialog.getWindow()
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}
