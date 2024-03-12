package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

val json = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
}



enum class JanusEvent(val value: String) {
    CREATE("create"),
    KEEP_ALIVE("keepalive"),
    SUCCESS("success"),
    MESSAGE("message"),
    EVENT("event"),
    ATTACH("attach"),
    REGISTERED("registered"),
    REGISTERING("registering"),
    UNREGISTER("unregister"),
    INCOMING_CALL("incomingcall"),
    CALLING("calling"),
    HANGUP("hangup"),
    ANSWER("answer"),
    RINGING("ringing"),
    PROGRESS("progress"),
    DTMF("dtmf"),
    MEDIA("media"),
    DETACH("detach"),
    DESTROY("destroy");

    fun value(): String {
        return name.toString().lowercase()
    }
}

@Serializable
open class JanusBase(
    @SerialName("janus")
    var janus: String = "",
) {


    fun decode(message: String): JanusBase {
        return Json.decodeFromString(message)
    }
}

fun decodeJanusMessage(message: String, callback: (JanusEvent, JanusBase) -> Unit) {
    val janusBase = json.decodeFromString<JanusBase>(message)
    when (janusBase.janus) {
        JanusEvent.MESSAGE.value() -> {

        }
        JanusEvent.EVENT.value() -> {
            val event = json.decodeFromString<EventBase>(message)

            if (event.plugindata?.data?.result?.event == JanusEvent.REGISTERING.value()) {
                // Registering
            } else if (event.plugindata?.data?.result?.event == JanusEvent.REGISTERED.value()) {
                //callback(JanusMethod.EVENT,success)
                val registerSuccess = json.decodeFromString<RegisterSuccess>(message)
                callback(JanusEvent.REGISTERED,registerSuccess)
            }
        }
        JanusEvent.SUCCESS.value() -> {
            val success = json.decodeFromString<TransactionSuccess>(message)
            if (message.contains("session_id")) {
                //from attach plugin
                callback(JanusEvent.ATTACH,success)

            } else {
                callback(JanusEvent.CREATE,success)
            }
        }
    }
}





