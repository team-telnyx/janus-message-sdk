package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class EventBase(
    @SerialName("plugindata")
    val plugindata: Plugindata? = null
) : JanusBase()

@Serializable
data class RegisterSuccess(
    @SerialName("sender")
    val sender: Long,
    @SerialName("session_id")
    val sessionId: Long
) : EventBase()

@Serializable
data class Plugindata(
    @SerialName("data")
    val data: Data,
    @SerialName("plugin")
    val plugin: String
) {
    @Serializable
    data class Data(
        @SerialName("result")
        val result: Result,
        @SerialName("sip")
        val sip: String
    ) {

        @Serializable
        data class Result(
            @SerialName("event")
            val event: String,
            @SerialName("master_id")
            val masterId: Long,
            @SerialName("register_sent")
            val registerSent: Boolean,
            @SerialName("username")
            val username: String
        )
    }
}