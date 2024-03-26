package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PluginData(
    @SerialName("data")
    val data: Data,
    @SerialName("plugin")
    val plugin: String
) {
    @Serializable
    data class Data(
        @SerialName("result")
        val result: Result,
        @SerialName("sip")
        val sip: String
    )
}
@Serializable
data class Result(
    @SerialName("event")
    val event: String,
    @SerialName("master_id")
    val masterId: Long,
    @SerialName("register_sent")
    val registerSent: Boolean,
    @SerialName("username")
    val username: String,
    @SerialName("headers")
    val headers: Headers
)
@Serializable
data class Headers(
    @SerialName("X-Telnyx-Leg-ID")
    var xTelnyxLegID: String,
    @SerialName("X-Telnyx-Session-ID")
    var xTelnyxSessionID: String
)
