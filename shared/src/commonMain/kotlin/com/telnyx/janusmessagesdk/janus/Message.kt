package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable

data class Message(
    @SerialName("body")
    var body: MessageBody,
    @SerialName("handle_id")
    var handleId: Long,
    @SerialName("janus")
    var janus: String,
    @SerialName("session_id")
    var sessionId: Long,
    @SerialName("transaction")
    var transaction: String
) {
    @Serializable
    data class MessageBody(
        @SerialName("content")
        var content: String,
        @SerialName("request")
        var request: String
    )
}