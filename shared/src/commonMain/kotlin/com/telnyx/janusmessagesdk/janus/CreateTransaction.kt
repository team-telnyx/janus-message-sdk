package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.uuid.UUID

@Serializable
data class CreateTransaction(
    @SerialName("janus")
    val janus: String = Janus.CREATE.value,
    @SerialName("transaction")
    val transaction: String =  UUID().toString()
)

@Serializable
data class Data(
    val id: Long
)

@Serializable
data class TransactionSuccess(
    val data: Data,
    val janus: String,
    @SerialName("transaction")
    val transaction: String,
    @SerialName("session_id")
    val sessionId: Long = 0L,
)