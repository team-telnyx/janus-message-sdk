package com.telnyx.janusmessagesdk.websocket

import com.telnyx.janusmessagesdk.janus.CreateTransaction
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.sendSerialized
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.plugins.websocket.wss
import io.ktor.http.HttpMethod
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import io.ktor.websocket.Frame
import io.ktor.websocket.readBytes
import io.ktor.websocket.readText
import kotlinx.serialization.json.Json

class WebSocketClient {

    val client = HttpClient(OkHttp) {
        install(WebSockets){
            contentConverter = KotlinxWebsocketSerializationConverter(Json)
        }

        install(ContentNegotiation) {
            json()
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    co.touchlab.kermit.Logger.v("DNG-Networking") { message }
                }
            }
            level = LogLevel.ALL
        }

    }

    suspend fun connect() {
        var sent = false;
        client.wss(method = HttpMethod.Get, port = 443, path = "v2", request = {
            url.protocol = io.ktor.http.URLProtocol.WSS
            url.host = "rtcdev.telnyx.com"
            headers.append("Sec-WebSocket-Protocol", "janus-protocol")
        }) {
            while (true){
                co.touchlab.kermit.Logger.v("DNG-Networking") { "Connected" }
                if (!sent) {
                    sendSerialized(CreateTransaction("").default())
                    sent = true
                }
                val frame = incoming.receive() as Frame.Text
                co.touchlab.kermit.Logger.v("DNG-Networking") { "Received: ${frame.readText()}" }
                co.touchlab.kermit.Logger.v("DNG-Networking") { "Sent" }
            }

        }
        client.close()
    }
}