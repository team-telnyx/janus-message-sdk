package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class EventBase(
    @SerialName("plugindata")
    val plugindata: PluginData? = null
) : JanusBase()

@Serializable
data class RegisterSuccess(
    @SerialName("sender")
    val sender: Long,
    @SerialName("session_id")
    val sessionId: Long
) : EventBase()

