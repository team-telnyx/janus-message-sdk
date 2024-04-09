package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.uuid.UUID

@Serializable
data class HangUpRequest(
    @SerialName("handle_id")
    var handleId: Long,
    @SerialName("session_id")
    var sessionId: Long,

) : JanusBase() {


    fun encode(): String {
        return json.encodeToString(this)
    }

    @SerialName("body")
    var body: HangUpBody? = null

    @SerialName("transaction")
    var transaction: String = ""
    fun default(): HangUpRequest {
        return this.apply {
            janus = Janus.MESSAGE.value
            body = HangUpBody(JanusEvent.HANGUP.value())
            transaction = UUID().toString()
        }
    }
    @Serializable
    data class HangUpBody(
        @SerialName("request")
        var request: String
    )
}

@Serializable
data class HangUpEvent(
    @SerialName("plugindata")
    var plugindata: HangUpPluginData,
    @SerialName("sender")
    var sender: Long,
    @SerialName("session_id")
    var sessionId: Long
) : JanusBase()
{
    @Serializable
    data class HangUpPluginData(
        @SerialName("data")
        var data: HangUpData,
        @SerialName("plugin")
        var plugin: String
    )
    @Serializable
    data class HangUpData(
        @SerialName("call_id")
        var callId: String,
        @SerialName("result")
        var result: HangUpDataResult,
        @SerialName("sip")
        var sip: String
    )

    @Serializable
    data class HangUpDataResult(
        @SerialName("code")
        var code: Int,
        @SerialName("event")
        var event: String? = null,
        @SerialName("reason")
        var reason: String? = null,
        @SerialName("reason_header")
        var reasonHeader: String? = null,
        @SerialName("reason_header_cause")
        var reasonHeaderCause: String? = null,
        @SerialName("reason_header_protocol")
        var reasonHeaderProtocol: String? = null
    )
}

