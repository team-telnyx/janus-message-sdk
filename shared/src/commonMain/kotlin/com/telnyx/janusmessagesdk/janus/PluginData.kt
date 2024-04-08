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
        @SerialName("call_id")
        val callId: String?  = null,
        @SerialName("result")
        val result: Result,
        @SerialName("sip")
        val sip: String? = null
    )
}
@Serializable
data class Result(
    @SerialName("event")
    val event: String,
    @SerialName("master_id")
    val masterId: Long? = null,
    @SerialName("register_sent")
    val registerSent: Boolean? = null,
    @SerialName("username")
    val username: String? = null,
    @SerialName("call_id")
    val callId: String? = null,
    @SerialName("callee")
    val callee: String? = null,
    @SerialName("displayname")
    val displayName: String? = null,
    @SerialName("headers")
    val headers: Headers? = null
)


@Serializable
data class Headers(
    @SerialName("X-Telnyx-Leg-ID")
    var xTelnyxLegID: String? = null,
    @SerialName("X-Telnyx-Session-ID")
    var xTelnyxSessionID: String? = null
)
