package com.example.mit_usos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Department : AppCompatActivity() {
    private var currentImage = R.drawable.class4420
    private var currentFloor = "III piętro"
    private var department = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_department)

        val departmentImage = findViewById<ImageView>(R.id.department_image)
        val floorView = findViewById<TextView>(R.id.floor_view)
        val changeViewButton = findViewById<Button>(R.id.change_view_button)

        val classNum = intent.extras?.getInt("class")
        when (classNum) {
            4420 -> {
                departmentImage.setImageResource(R.drawable.class4420)
                currentImage = R.drawable.class4420
                floorView.text = "III piętro"
            }
            else -> {
                departmentImage.setImageResource(R.drawable.class5840)
                currentImage = R.drawable.class5840
                floorView.text = "IV piętro"
            }
        }

        currentFloor = floorView.text as String

        changeViewButton.setOnClickListener {
            if (department) {
                departmentImage.setImageResource(R.drawable.class_view)
                floorView.text = "Sala " + classNum
                changeViewButton.text = "Widok wydziału"
                department = false
            } else {
                departmentImage.setImageResource(currentImage)
                floorView.text = currentFloor
                changeViewButton.text = "Widok sali"
                department = true
            }
        }

    }
}