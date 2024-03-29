package com.telnyx.janusmessagesdk.janus


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.uuid.UUID

@Serializable
data class Accept(
    @SerialName("body")
    val body: CallBody,
    @SerialName("jsep")
    val jsep: Jsep
) : JanusBase() {
    fun encode(): String {
        return json.encodeToString(this)
    }

    @SerialName("session_id")
    var sessionId: Long = 0

    @SerialName("transaction")
    var transaction: String = ""

    @SerialName("handle_id")
    var handleId: Long = 0

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

@Serializable
data class Call(
    @SerialName("body")
    val body: CallBody,
    @SerialName("jsep")
    val jsep: Jsep
) : JanusBase() {
    fun encode(): String {
        return json.encodeToString(this)
    }

    @SerialName("session_id")
    var sessionId: Long = 0

    @SerialName("autoaccept_reinvites")
    var autoAcceptReInvites: Boolean = false


    @SerialName("transaction")
    var transaction: String = ""

    @SerialName("handle_id")
    var handleId: Long = 0

    fun default(handleId: Long, body: CallBody, jsep: Jsep, sessionId: Long): Call {
        return Call(
            body = body,
            jsep = jsep
        ).apply {
            this.handleId = handleId
            janus = Janus.MESSAGE.value
            this.sessionId = sessionId
            transaction = UUID().toString()
            autoAcceptReInvites = false
        }
    }
}

@Serializable
data class Jsep(
    @SerialName("sdp")
    val sdp: String,
    @SerialName("type")
    val type: String
)

@Serializable
class CallBody() {

    @SerialName("request")
    var request: String = ""

    @SerialName("uri")
    var uri: String = ""

    @SerialName("autoaccept_reinvites")
    var autoacceptReinvites: Boolean = false
    fun default(userName: String,janusRequest:String = Janus.CALL.value): CallBody {
        return CallBody().apply {
            this.autoacceptReinvites = false
            request = janusRequest
            uri = "sip:$userName@sipdev.telnyx.com"
        }
    }

    fun encode():String{
        return json.encodeToString(this)
    }
}