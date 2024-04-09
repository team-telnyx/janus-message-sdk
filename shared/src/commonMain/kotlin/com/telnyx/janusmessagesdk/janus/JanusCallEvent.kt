package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.uuid.UUID
@Serializable
data class JanusCallEvent(
    @SerialName("body")
    val pluginData: PluginData? = null,
    @SerialName("jsep")
    val jsep: Jsep? = null
) : EventBase() {
    fun encode(): String {
        return json.encodeToString(this)
    }

    @SerialName("session_id")
    var sessionId: Long = 0

    @SerialName("sender")
    var sender: Long = 0

    fun default(handleId: Long, body: CallBody, jsep: Jsep, sessionId: Long): JanusCall {
        return JanusCall(
            body = body,
            jsep = jsep
        ).apply {
            this.handleId = handleId
            janus = Janus.MESSAGE.value
            this.sessionId = sessionId
            transaction = UUID().toString()
        }
    }
}