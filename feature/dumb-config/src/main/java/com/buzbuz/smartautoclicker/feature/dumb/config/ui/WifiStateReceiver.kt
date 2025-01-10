package com.buzbuz.smartautoclicker.feature.dumb.config.ui
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.util.Log


class WifiConnectionReceiver : BroadcastReceiver() {
    companion object {
        var isDisconnected = false
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (WifiManager.NETWORK_STATE_CHANGED_ACTION == intent.action) {
            val networkInfo: NetworkInfo? = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO)
            if (networkInfo?.isConnected == true) {
                // WiFi is connected
                Log.d("WifiConnectionReceiver", "WiFi is connected")
                if (isDisconnected) {
                    listener?.invoke()
                }
            } else {
                // WiFi is disconnected
                isDisconnected = true
                Log.d("WifiConnectionReceiver", "WiFi is disconnected")
            }
        }
    }

    var listener: (()->Unit)? = null

    fun setCallback(myCallback: () -> Unit) {
        this.listener = myCallback
    }
}