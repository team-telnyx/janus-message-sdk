package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.uuid.UUID


@Serializable
data class Trickle(
    @SerialName("candidate")
    val candidate: Candidate,
) : JanusBase()
{

    @SerialName("handle_id")
    var handleId: Long = 0
    @SerialName("session_id")
    var sessionId: Long = 0
    @SerialName("transaction")
    var transaction: String   = ""

    fun default(handleId: Long, sessionId: Long, candidate: Candidate): Trickle {
        return Trickle(
            candidate = candidate
        ).apply {
            this.handleId = handleId
            this.sessionId = sessionId
            this.transaction = UUID().toString()
        }
    }
}

@Serializable
data class Candidate(
    @SerialName("candidate")
    val candidate: String,
    @SerialName("sdpMLineIndex")
    val sdpMLineIndex: Int,
    @SerialName("sdpMid")
    val sdpMid: String
)