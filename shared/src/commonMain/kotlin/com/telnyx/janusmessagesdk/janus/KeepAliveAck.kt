package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class KeepAliveAck(
    val janus: String,
    @SerialName("session_id")
    val sessionId: Long,
    @SerialName("transaction")
    val transaction: String
)

