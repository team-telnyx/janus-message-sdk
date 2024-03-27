package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString

@Serializable
data class HangUpRequest(
    @SerialName("body")
    var body: Body,
    @SerialName("handle_id")
    var handleId: Long,
    @SerialName("session_id")
    var sessionId: Long,
    @SerialName("transaction")
    var transaction: String
) : JanusBase() {

    fun encode(): String {
        return json.encodeToString(this)
    }

    fun default(handleId: Long, sessionId: Long): HangUpRequest {
        return HangUpRequest(
            body = Body(JanusEvent.HANGUP.value()),
            handleId = handleId,
            sessionId = sessionId,
            transaction = transaction
        ).apply {
            janus = Janus.MESSAGE.value
        }
    }
    @Serializable
    data class Body(
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