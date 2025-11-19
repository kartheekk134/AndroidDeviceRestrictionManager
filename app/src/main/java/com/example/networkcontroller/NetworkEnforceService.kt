package com.networkcontroller

import Helper.isAirplaneModeOn
import Helper.isBluetoothEnabled
import Helper.isWifiEnabled
import Helper.toggleBluetooth
import Helper.toggleWifi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.UserManager
import androidx.annotation.RequiresApi

class NetworkEnforcerService : Service() {

    private val handler = Handler(Looper.getMainLooper())
    private val checkInterval = 60_000L // every 10 seconds

    override fun onBind(intent: Intent?): IBinder? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        enableUserRestrictions()
        startForegroundService()
        startRepeatingCheck()
    }

    private fun enableUserRestrictions() {
        val dpm = getSystemService(DevicePolicyManager::class.java)
        val admin = ComponentName(this, MyDeviceAdminReceiver::class.java)
        dpm.setUninstallBlocked(admin, packageName, true)
        dpm.addUserRestriction(admin, UserManager.DISALLOW_AIRPLANE_MODE)
        dpm.addUserRestriction(admin, UserManager.DISALLOW_INSTALL_UNKNOWN_SOURCES)
        dpm.addUserRestriction(admin, UserManager.DISALLOW_INSTALL_APPS)
        dpm.setUninstallBlocked(admin, "com.whatsapp", true)
        dpm.addUserRestriction(admin, UserManager.DISALLOW_FACTORY_RESET)
        dpm.setStatusBarDisabled(admin, true)
    }

    private fun startRepeatingCheck() {
        handler.post(object : Runnable {
            override fun run() {
                enforceNetworkRules()
                handler.postDelayed(this, checkInterval)
            }
        })
    }

    private fun enforceNetworkRules() {
        val ctx = applicationContext

        // Wi-Fi Check
        if (!isWifiEnabled(ctx)) {
            toggleWifi(ctx, true) // turn ON (if possible)
        }

        // Bluetooth Check
        if (isBluetoothEnabled()) {
            toggleBluetooth() // turn OFF
        }

        // Airplane Mode Check (cannot auto-toggle)
        if (isAirplaneModeOn(ctx)) {
           /* val intent = Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)*/
            /*val enable = false

            val dpm = ctx.getSystemService(DevicePolicyManager::class.java)
            val admin = ComponentName(ctx, MyDeviceAdminReceiver::class.java)

            dpm.setGlobalSetting(
                admin,
                Settings.Global.AIRPLANE_MODE_ON,
                "0"
            )

            ctx.sendBroadcast(
                Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED).apply {
                    putExtra("state", enable)
                }
            )*/

            if (!isWifiEnabled(ctx)) {
                toggleWifi(ctx, true) // turn ON (if possible)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startForegroundService() {
        val channelId = "network_enforcer_channel"
        val channel = NotificationChannel(
            channelId,
            "Network Enforcer",
            NotificationManager.IMPORTANCE_LOW
        )
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)

        val notification = Notification.Builder(this, channelId)
            .setContentTitle("Network Protection Active")
            .setContentText("Monitoring connectivity settings…")
            .setSmallIcon(android.R.drawable.stat_sys_warning)
            .build()

        startForeground(1, notification)

    }
}
