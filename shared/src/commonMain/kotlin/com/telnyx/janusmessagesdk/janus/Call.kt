package com.telnyx.janusmessagesdk.janus


import com.google.gson.annotations.SerializedName

data class Call(
    @SerializedName("body")
    val body: Body,
    @SerializedName("handle_id")
    val handleId: Long,
    @SerializedName("janus")
    val janus: String,
    @SerializedName("jsep")
    val jsep: Jsep,
    @SerializedName("session_id")
    val sessionId: Long,
    @SerializedName("transaction")
    val transaction: String
) {
    data class Body(
        @SerializedName("autoaccept_reinvites")
        val autoacceptReinvites: Boolean,
        @SerializedName("request")
        val request: String,
        @SerializedName("uri")
        val uri: String
    )

    data class Jsep(
        @SerializedName("sdp")
        val sdp: String,
        @SerializedName("type")
        val type: String
    )
}