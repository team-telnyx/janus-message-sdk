package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

val json = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
}

enum class JanusEventType(val value: String){
    SUCCESS("success"),
    ERROR("error"),
    EVENT("event"),
    MESSAGE("message"),
    ACK("ack")
}

enum class Janus(val value: String) {
    ATTACH("attach"),
    DETACH("detach"),
    DESTROY("destroy"),
    KEEP_ALIVE("keepalive"),
    REGISTER("register"),
    UNREGISTER("unregister"),
    CREATE("create"),
    MESSAGE("message"),
    SUCCESS("success"),
    ERROR("error"),
    CALL("call"),

}

enum class JanusEvent(val value: String) {
    SESION_CREATED("create"),
    ACKNOWLEDGED("ack"),
    PLUGIN_ATTACHED("attach"),
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
        JanusEventType.MESSAGE.value -> {

        }
        JanusEventType.EVENT.value -> {
            val event = json.decodeFromString<EventBase>(message)

            if (event.plugindata?.data?.result?.event == JanusEvent.REGISTERING.value()) {
                // Registering
            } else if (event.plugindata?.data?.result?.event == JanusEvent.REGISTERED.value()) {
                //callback(JanusMethod.EVENT,success)
                val registerSuccess = json.decodeFromString<RegisterSuccess>(message)
                callback(JanusEvent.REGISTERED,registerSuccess)
            }
        }
        JanusEventType.SUCCESS.value -> {
            val result = json.decodeFromString<TransactionSuccess>(message)
            if (message.contains("session_id")) {
                //from attach plugin
                callback(JanusEvent.PLUGIN_ATTACHED,result)

            } else {
                callback(JanusEvent.SESION_CREATED,result)
            }
        }
        JanusEventType.ACK.value -> {
            val result = json.decodeFromString<TransactionSuccess>(message)
            callback(JanusEvent.ACKNOWLEDGED,result)
        }
    }
}





