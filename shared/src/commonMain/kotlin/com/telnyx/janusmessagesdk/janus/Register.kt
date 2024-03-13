package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.uuid.UUID


@Serializable
data class Register(
    @SerialName("body")
    val body: Body,
    @SerialName("handle_id")
    val handleId: Long,
    @SerialName("session_id")
    val sessionId: Long = UUID().timeStamp,
    @SerialName("transaction")
    val transaction: String = UUID().toString()
) : JanusBase() {
    fun encode(): String {
        return json.encodeToString(this)
    }

    fun default(handleId: Long, body: Body): Register {
        return Register(
            body = body,
            handleId = handleId,
        ).apply {
            janus = Janus.MESSAGE.value
        }
    }

}

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
) {

    init {
        
    }
    fun default(login: String, loginToken: String,displayName: String, ): Body {
        return Body(
            displayName = displayName,
            loginToken = loginToken,
            login = login,
            proxy = "sip:sipdev.telnyx.com:5060",
            username = "sip:$login@sipdev.telnyx.com",
            request = Janus.REGISTER.value
        )
    }
}

