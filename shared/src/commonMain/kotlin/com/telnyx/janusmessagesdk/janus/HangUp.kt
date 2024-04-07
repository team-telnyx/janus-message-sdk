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
    @SerialName("reason")
    var reason: String,
    @SerialName("sender")
    var sender: Long,
    @SerialName("session_id")
    var sessionId: Long
) : JanusBase() {
    fun encode(): String {
        return json.encodeToString(this)
    }
}