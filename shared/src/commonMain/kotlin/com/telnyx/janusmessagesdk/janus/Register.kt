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
        return this.apply {
            janus = Janus.MESSAGE.value
            this.handleId = handleId
            this.body = body
            transaction = UUID().toString()
        }
    }

}





@Serializable
class Body{
    @SerialName("proxy")
    var proxy: String? = null
    @SerialName("request")
    var request: String? = null
    @SerialName("login")
    var login: String? = null
    @SerialName("display_name")
    var displayName: String? = ""
    @SerialName("username")
    var username: String ?= null
    @SerialName("password")
    var password: String ?= null
    fun default(userName:String,password:String,displayName: String = ""): Body {
        return this.apply {
            this.login = userName
            this.password = password
            this.username = "sip:$userName@sipdev.telnyx"
            request = Janus.REGISTER.value
            proxy = "sip:sipdev.telnyx.com:5060"
            this.displayName = displayName
        }
    }
}

