package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.uuid.UUID


@Serializable
data class SipPlugin(
    val janus: String,
    @SerialName("opaque_id")
    val opaqueId: String,
    @SerialName("plugin")
    val plugin: String,
    @SerialName("session_id")
    val sessionId: Long,
    @SerialName("transaction")
    val transaction: String
) {
    fun encode(): String {
        return Json.encodeToString(this)
    }

    fun default(sessionId: Long): SipPlugin {
        return SipPlugin(
            janus = JanusEvent.ATTACH.name.lowercase(),
            opaqueId = "sip-${UUID().toString()}",
            plugin = "janus.plugin.sip",
            sessionId = UUID().timeStamp,
            transaction = UUID().toString()
        )
    }
}