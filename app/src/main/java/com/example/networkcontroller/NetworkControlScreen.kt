import Helper.isAirplaneModeOn
import Helper.isBluetoothEnabled
import Helper.isWifiEnabled
import Helper.toggleBluetooth
import Helper.toggleWifi
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.provider.Settings.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NetworkControlScreen(context: Context) {

    var wifiOn by remember { mutableStateOf(isWifiEnabled(context)) }
    var bluetoothOn by remember { mutableStateOf(isBluetoothEnabled()) }
    var airplaneOn by remember { mutableStateOf(isAirplaneModeOn(context)) }

    LaunchedEffect(Unit) {
        // Refresh state whenever UI loads
        wifiOn = isWifiEnabled(context)
        bluetoothOn = isBluetoothEnabled()
        airplaneOn = isAirplaneModeOn(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // WIFI BUTTON
        Button(
            onClick = {
                wifiOn = isWifiEnabled(context)
                toggleWifi(context, !wifiOn)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            Text(if (wifiOn) "Wi-Fi ON" else "Wi-Fi OFF", fontSize = 22.sp)
        }

        // BLUETOOTH BUTTON
        Button(
            onClick = {
                toggleBluetooth()
                bluetoothOn = isBluetoothEnabled()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            Text(if (bluetoothOn) "Bluetooth ON" else "Bluetooth OFF", fontSize = 22.sp)
        }

        // AIRPLANE MODE BUTTON
        Button(
            onClick = {
                val intent = Intent(ACTION_AIRPLANE_MODE_SETTINGS)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            Text(if (airplaneOn) "Airplane Mode ON" else "Airplane Mode OFF", fontSize = 22.sp)
        }
    }
}
