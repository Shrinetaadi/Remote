package com.catvisionindia.remote

import android.Manifest
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.ConsumerIrManager
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_start.*


class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        requestPermission()

        val imgUrl = "https://catvisiononline.in/wp-content/uploads/mobileapp/product.jpg"
        Picasso.get().load(imgUrl).placeholder(R.drawable.product).resize(1000, 1000)
            .error(R.drawable.product).into(imgContent)
        adBanner.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("http://www.catvisiononline.in"))
            startActivity(browserIntent)
        }
        adBanr.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("http://www.catvisiononline.in"))
            startActivity(browserIntent)
        }

        imgContent.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("http://www.catvisiononline.in"))
            startActivity(browserIntent)
        }
        val irManager: ConsumerIrManager =
            this@StartActivity.getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager
        imgRemote.setOnClickListener {
            if (irManager.hasIrEmitter()) {
                val intent = Intent(this, MainActivity::class.java)
                // start your next activity
                startActivity(intent)
            }else {
                val alertDialog = AlertDialog.Builder(this)

                alertDialog.apply {
                    setIcon(R.drawable.ic_cancel_dialog)
                    setTitle("Alert")
                    setMessage("Your device doesn't have a IR Blaster, Hence it is not supported.")
                    setPositiveButton("OK") { _, _ ->
                        toast("This device is not supported.")
                    }
                }.create().show()


            }
        }


    }
    private fun toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

    private fun hasIRPermission(): Boolean {

        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.TRANSMIT_IR
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun hasInternetPermission(): Boolean {

        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.INTERNET
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun hasVibratorPermission(): Boolean {

        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.VIBRATE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {

        var permission = mutableListOf<String>()
        if (!hasIRPermission()) {
            permission.add(Manifest.permission.TRANSMIT_IR)
        }
        if (!hasVibratorPermission()) {
            permission.add(Manifest.permission.VIBRATE)
        }
        if (!hasInternetPermission()) {
            permission.add(Manifest.permission.INTERNET)
        }

        if (permission.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permission.toTypedArray(), 0)
        }


    }


    override fun onBackPressed() {
        finish()
    }
}