package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MediaEvent(
    @SerialName("janus")
    var janus: String,
    @SerialName("mid")
    var mid: String,
    @SerialName("receiving")
    var receiving: Boolean,
    @SerialName("sender")
    var sender: Long,
    @SerialName("session_id")
    var sessionId: Long,
    @SerialName("type")
    var type: String
)