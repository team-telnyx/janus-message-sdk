package com.telnyx.janusmessagesdk.janus


import com.google.gson.annotations.SerializedName

data class TrickleIce(
    @SerializedName("candidate")
    val candidate: Candidate,
    @SerializedName("handle_id")
    val handleId: Long,
    @SerializedName("janus")
    val janus: String,
    @SerializedName("session_id")
    val sessionId: Long,
    @SerializedName("transaction")
    val transaction: String
) {
    data class Candidate(
        @SerializedName("candidate")
        val candidate: String,
        @SerializedName("sdpMLineIndex")
        val sdpMLineIndex: Int,
        @SerializedName("sdpMid")
        val sdpMid: String
    )
}