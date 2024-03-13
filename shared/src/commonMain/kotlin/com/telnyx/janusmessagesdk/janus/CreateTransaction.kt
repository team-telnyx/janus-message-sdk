package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.uuid.UUID


@Serializable
data class CreateTransaction(
    @SerialName("transaction")
    val transaction: String
) : JanusBase()  {

    //for ios objective-c does not support default values in parameters
    fun default(): CreateTransaction {
        return CreateTransaction(transaction =  UUID().toString()).apply {
            janus = Janus.CREATE.value
        }
    }

    fun encode(): String {
        return json.encodeToString(serializer(),this)
    }

}


@Serializable
data class Data(
    val id: Long
)

@Serializable
data class TransactionSuccess(
    val data: Data,
    @SerialName("transaction")
    val transaction: String,
    @SerialName("session_id")
    val sessionId: Long = 0L,
) : JanusBase(Janus.SUCCESS.value) {
    fun encode(): String {
        return json.encodeToString(this)
    }

}

