package com.example.trafa

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.ui.*
import com.example.trafa.databinding.ActivityMainBinding
import com.example.trafa.ui.Camera.SaveImageCamera
import com.example.trafa.ui.setting.SettingActivity

class MainActivity : AppCompatActivity() {

    private val cameraRequestId = 1222
    private lateinit var cameraBtn: Button
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
//    private lateinit var myImage: ImageView


//    //pemanggilan untuk save hasil kamera
//    private lateinit var saveImageCamera: SaveImageCamera
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

//        saveImageCamera = SaveImageCamera()


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_favorite, R.id.nav_slideshow, R.id.nav_info
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //pemanggilan fungsi kamera
        cameraBtn = findViewById(R.id.cameraBtn)

        if (ContextCompat.checkSelfPermission(
                applicationContext, android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.CAMERA), cameraRequestId
            )

        }

// Pemanggilan Id dari myImage di halaman save_image_camera.xml
 //       myImage = binding.myImage



        // set camera open
        cameraBtn.setOnClickListener {
            val cameraInt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraInt, cameraRequestId)

        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == cameraRequestId) {
////
////            //save image di dalam class saveImageCamera
////            val images: Bitmap = data?.extras?.get("data") as Bitmap
////            saveImageCamera.saveImage(images)
////
////          //  val myImage = findViewById<ImageView>(R.id.myImage) // Mengakses elemen myImage dari save_image_camera.xml
////            myImage.setImageBitmap(images) // Mengatur gambar pada elemen myImage
//
//
//        }
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                Intent(this, SettingActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
