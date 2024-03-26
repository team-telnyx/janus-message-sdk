package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.encodeToString
import kotlinx.uuid.UUID

data class OfferEvent(
    @SerialName("body")
    val pluginData: PluginData,
    @SerialName("jsep")
    val jsep: Jsep
) : EventBase() {
    fun encode(): String {
        return json.encodeToString(this)
    }

    @SerialName("session_id")
    var sessionId: Long = 0

    @SerialName("sender")
    var sender: Long = 0

    fun default(handleId: Long, body: CallBody, jsep: Jsep, sessionId: Long): Call {
        return Call(
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