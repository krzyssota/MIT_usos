package com.example.mit_usos.ui.schedule

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mit_usos.Department
import com.example.mit_usos.R
import com.example.mit_usos.ui.gallery.GalleryFragment

class ScheduleFragment : Fragment () {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)
        val button = view.findViewById<View>(R.id.button)

        button.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, Department::class.java)
            startActivity(intent)
        })

        return view
    }
}