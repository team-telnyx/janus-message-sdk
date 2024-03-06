package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.uuid.UUID


@Serializable
data class SipPlugin(
    val janus: String = "attach",
    @SerialName("opaque_id")
    val opaqueId: String = "sip-${UUID().toString()}",
    @SerialName("plugin")
    val plugin: String = "janus.plugin.sip",
    @SerialName("session_id")
    val sessionId: Long,
    @SerialName("transaction")
    val transaction: String
)