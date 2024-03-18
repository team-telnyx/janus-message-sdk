package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.uuid.UUID


@Serializable
data class Register(

    @SerialName("session_id")
    val sessionId: Long = UUID().timeStamp,

) : JanusBase() {
    fun encode(): String {
        return json.encodeToString(this)
    }

    @SerialName("handle_id")
    var handleId: Long? = null

    @SerialName("body")
    var body: Body? = null

    @SerialName("transaction")
    var transaction: String = UUID().toString()

    fun default(handleId: Long, body: Body): Register {
        return Register(
        ).apply {
            janus = Janus.MESSAGE.value
            this.handleId = handleId
            this.body = body
            transaction = UUID().toString()
        }
    }

}

@Serializable
data class Body(
    @SerialName("username")
    val username: String ?= null,
    @SerialName("login_token")
    val loginToken: String?,
    @SerialName("login")
    val login: String?,

) {

    @SerialName("proxy")
    var proxy: String? = null
    @SerialName("request")
    var request: String? = null
    fun default(login: String, loginToken: String,displayName: String, ): Body {
        return Body(
            loginToken = loginToken,
            login = login,
            username = "sip:$login@sipdev.telnyx.com",
        ).apply {
            request = Janus.REGISTER.value
            proxy = "sip:sipdev.telnyx.com:5060"
        }
    }
}

