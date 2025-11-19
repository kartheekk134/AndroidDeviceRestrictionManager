# AndroidDeviceRestrictionManager
A Device Owner–based Android application designed to secure, control, and restrict system settings on enterprise or dedicated-use devices. This app helps prevent users from accessing or modifying critical settings like Airplane Mode, WiFi configuration, Bluetooth options, and more. It is useful for POS terminals, kiosk devices, work devices, and any environment where full device control is required.

#📌 Purpose of This App

Modern Android devices allow users to toggle important system settings freely. In business environments, this can cause issues such as network disconnections, device misuse, or accidental shutdown of essential features.

This app solves that problem by using Device Owner permissions to enforce system restrictions and maintain a fully controlled environment.

#✨ Key Features
1. Disable Airplane Mode Functionality

Prevents the user from turning ON or OFF airplane mode.

Ensures the device stays connected (WiFi/Mobile data not affected).

2. Restrict System Settings

You can block the user from changing:

WiFi settings

Mobile data configuration

Bluetooth

Tethering/Hotspot

USB file transfer

Date & time settings

3. Prevent App Uninstallation

Ensures the main application cannot be removed by the user.

4. Kiosk Mode Support

Lock the device to a single app.

Prevent Home/Recent button usage.

Ideal for dedicated devices like POS systems or kiosks.

5. Enhanced Device Security

Disable camera (optional)

Block installation of unknown apps

Disable power button actions

Disable factory reset

🔧 How It Works

This app runs as a Device Owner, which gives it the highest level of control on Android devices.
With Device Policy APIs, the app enforces restrictions and secures device configurations so that users cannot modify critical settings.

#🎯 Use Cases

Payment terminals (POS / PED devices)

Kiosk devices in shops or malls

Delivery and logistics devices

Attendance/Check-in systems

Enterprise work devices

Tablets used for single-purpose applications

#📄 Disclaimer

This application is intended only for enterprise use or controlled environments.
It should not be installed on personal devices, as Device Owner mode will restrict system-level settings.
