package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString

enum class HoldRequestType(val value: String) {
    HOLD("hold"),
    UNHOLD("unhold")
}

@Serializable
data class HoldRequest(
    val request: String,
    val direction:String,
) {
    fun encode(): String {
        return json.encodeToString(this)
    }
}