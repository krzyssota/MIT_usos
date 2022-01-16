package com.example.mit_usos.ui.schedule

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.mit_usos.Department
import com.example.mit_usos.R

class ScheduleFragment : Fragment () {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)
        val mrjpButton = view.findViewById<Button>(R.id.mrjp_button)
        val zpoButton = view.findViewById<Button>(R.id.zpo_button)

        mrjpButton.setOnClickListener {
            openDepartmentView(4420)
        }

        zpoButton.setOnClickListener {
            openDepartmentView(5840)
        }

        return view
    }

    private fun openDepartmentView(classNum: Int) {
        val intent = Intent(activity, Department::class.java)
        intent.putExtra("class", classNum)
        startActivity(intent)
    }
}