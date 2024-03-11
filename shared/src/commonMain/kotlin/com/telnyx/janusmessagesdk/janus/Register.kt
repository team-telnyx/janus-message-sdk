package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.uuid.UUID


@Serializable
data class Register(
    @SerialName("body")
    val body: Body,
    @SerialName("handle_id")
    val handleId: Long,
    @SerialName("janus")
    val janus: String,
    @SerialName("session_id")
    val sessionId: Long = UUID().timeStamp,
    @SerialName("transaction")
    val transaction: String = UUID().toString()
) 

@Serializable
data class Body(
    @SerialName("display_name")
    val displayName: String,
    @SerialName("proxy")
    val proxy: String = "sip:sipdev.telnyx.com:5060",
    @SerialName("request")
    val request: String = "register",
    @SerialName("login_token")
    val loginToken: String,
    @SerialName("login")
    val login: String,
    @SerialName("username")
    val username: String = "sip:$login@sipdev.telnyx.com"
)