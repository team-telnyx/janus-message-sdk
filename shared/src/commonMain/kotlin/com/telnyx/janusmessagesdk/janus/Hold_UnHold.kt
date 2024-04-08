package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.Serializable

enum class HoldRequestType(val value: String) {
    HOLD("hold"),
    UNHOLD("unhold")
}

@Serializable
data class HoldRequest(
    val request: String,
    val direction:String,
)