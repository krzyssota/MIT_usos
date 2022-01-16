package com.example.mit_usos

import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.mit_usos.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.help_dialog.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            showDialog(this@MainActivity)
        }




        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun showDialog(activity: Activity?) {
        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.help_dialog)

        val access = dialog.findViewById(R.id.access) as TextView
        val access1 = dialog.findViewById(R.id.access1) as TextView
        val access2 = dialog.findViewById(R.id.access2) as TextView
        access.setVisibility(View.GONE) ;
        access1.setVisibility(View.GONE) ;
        access2.setVisibility(View.INVISIBLE) ;



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
               this@MainActivity,
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