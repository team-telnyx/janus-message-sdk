package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Register(
    @SerialName("body")
    val body: Body,
    @SerialName("handle_id")
    val handleId: Long,
    @SerialName("janus")
    val janus: String,
    @SerialName("session_id")
    val sessionId: Long,
    @SerialName("transaction")
    val transaction: String
) 

@Serializable
data class Body(
    @SerialName("authuser")
    val authuser: String,
    @SerialName("proxy")
    val proxy: String = "sip:sipdev.telnyx.com:5060",
    @SerialName("request")
    val request: String = "register",
    @SerialName("secret")
    val secret: String,
    @SerialName("username")
    val username: String
)