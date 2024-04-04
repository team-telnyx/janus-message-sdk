package com.telnyx.janusmessagesdk.android


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.telnyx.janusmessagesdk.websocket.WebSocketClient
import com.telnyx.video.sdk.Room
import com.telnyx.video.sdk.webSocket.model.send.DisplayParameters
import com.telnyx.video.sdk.webSocket.model.send.ExternalData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch(Dispatchers.IO) {
            WebSocketClient().connect()
        }

    }
}