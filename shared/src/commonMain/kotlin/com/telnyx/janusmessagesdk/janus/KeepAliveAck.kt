package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.uuid.UUID


@Serializable
data class KeepAliveAck(
    @SerialName("transaction")
    val transaction: String
) : JanusBase() {

    @SerialName("session_id")
    var sessionId: Long = 0
    fun encode(): String {
        return json.encodeToString(this)
    }

    fun default(sessionId: Long): KeepAliveAck {
        return KeepAliveAck(
            transaction = UUID().toString()
        ).apply {
            this.sessionId = sessionId
            janus = Janus.KEEP_ALIVE.value
        }
    }
}


@Serializable
data class Ack(
    @SerialName("session_id")
    var sessionId: Long,
    @SerialName("transaction")
    var transaction: String
) : JanusBase() {
    fun encode(): String {
        return json.encodeToString(this)
    }
}