package com.telnyx.janusmessagesdk.android


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.telnyx.video.sdk.Room
import com.telnyx.video.sdk.webSocket.model.send.DisplayParameters
import com.telnyx.video.sdk.webSocket.model.send.ExternalData
import java.util.UUID


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val si = DisplayParameters(
            participantId = "docendi",
            telephonyEngineParticipant = null,
            external = null,
            stream = null,
            canReceiveMessages = null
        )

    }
}