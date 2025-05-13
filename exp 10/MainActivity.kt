package com.example.exp10


import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 1

    private lateinit var imeiTextView: TextView
    private lateinit var simSerialTextView: TextView
    private lateinit var networkCountryTextView: TextView
    private lateinit var networkOperatorTextView: TextView
    private lateinit var simCountryTextView: TextView
    private lateinit var phoneTypeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bind views
        imeiTextView = findViewById(R.id.imeiTextView)
        simSerialTextView = findViewById(R.id.simSerialTextView)
        networkCountryTextView = findViewById(R.id.networkCountryTextView)
        networkOperatorTextView = findViewById(R.id.networkOperatorTextView)
        simCountryTextView = findViewById(R.id.simCountryTextView)
        phoneTypeTextView = findViewById(R.id.phoneTypeTextView)

        // Check permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_PHONE_STATE),
                REQUEST_CODE
            )
        } else {
            showTelephonyInfo()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            showTelephonyInfo()
        } else {
            imeiTextView.text = "Permission denied to read phone state."
        }
    }

    private fun showTelephonyInfo() {
        val tm = getSystemService(TELEPHONY_SERVICE) as TelephonyManager

        imeiTextView.text = "IMEI: ${getSafeImei(tm)}"
        simSerialTextView.text = "SIM Serial Number: ${tm.simSerialNumber ?: "Unavailable"}"
        networkCountryTextView.text = "Network Country ISO: ${tm.networkCountryIso}"
        networkOperatorTextView.text = "Network Operator: ${tm.networkOperatorName}"
        simCountryTextView.text = "SIM Country ISO: ${tm.simCountryIso}"
        phoneTypeTextView.text = "Phone Type: ${getPhoneTypeName(tm.phoneType)}"
    }

    private fun getSafeImei(tm: TelephonyManager): String {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                tm.imei ?: "Unavailable"
            } else {
                @Suppress("DEPRECATION")
                tm.deviceId ?: "Unavailable"
            }
        } catch (e: SecurityException) {
            "Restricted"
        }
    }

    private fun getPhoneTypeName(type: Int): String = when (type) {
        TelephonyManager.PHONE_TYPE_GSM -> "GSM"
        TelephonyManager.PHONE_TYPE_CDMA -> "CDMA"
        TelephonyManager.PHONE_TYPE_SIP -> "SIP"
        TelephonyManager.PHONE_TYPE_NONE -> "None"
        else -> "Unknown"
    }
}
