import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.net.wifi.WifiManager
import android.provider.Settings

object Helper {

    fun isWifiEnabled(context: Context): Boolean {
        val wm = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return wm.isWifiEnabled
    }


    fun toggleWifi(context: Context, enable: Boolean) {
        val wm = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
//        wm.isWifiEnabled = !wm.isWifiEnabled
        wm.setWifiEnabled(enable)
    }



    fun isBluetoothEnabled(): Boolean =
        BluetoothAdapter.getDefaultAdapter()?.isEnabled == true


    fun toggleBluetooth() {
        BluetoothAdapter.getDefaultAdapter()?.apply {
            if (isEnabled) disable() else enable()
        }
    }



    fun isAirplaneModeOn(context: Context): Boolean =
        Settings.Global.getInt(
            context.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON, 0
        ) != 0
}