package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.uuid.UUID


@Serializable
data class KeepAliveAck(
    @SerialName("session_id")
    val sessionId: Long,
    @SerialName("transaction")
    val transaction: String
) : JanusBase() {
    fun encode(): String {
        return json.encodeToString(this)
    }

    fun default(sessionId: Long): KeepAliveAck {
        return KeepAliveAck(
            sessionId = sessionId,
            transaction = UUID().toString()
        ).apply {
            janus = Janus.KEEP_ALIVE.value
        }
    }
}

