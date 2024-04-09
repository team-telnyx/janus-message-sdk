package com.telnyx.janusmessagesdk.janus

import co.touchlab.kermit.Logger
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

val json = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    isLenient = true
}

enum class JanusEventType(val value: String){
    SUCCESS("success"),
    ERROR("error"),
    EVENT("event"),
    MESSAGE("message"),
    MEDIA("media"),
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
    OFFER("offer"),

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
    ACCEPTED("accepted"),
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

@Throws(Exception::class)
fun decodeJanusMessage(message: String, callback: (JanusEvent, JanusBase) -> Unit) {
    val janusBase = json.decodeFromString<JanusBase>(message)
    Logger.i { "Received Message : $message" }
    when (janusBase.janus) {
        JanusEventType.MESSAGE.value -> {

        }
        JanusEventType.MEDIA.value -> {
            Logger.i { "Hello World" }

        }
        JanusEventType.EVENT.value -> {
            val event = json.decodeFromString<EventBase>(message)

            when (event.plugindata?.data?.result?.event) {
                JanusEvent.REGISTERING.value() -> {
                    // Registering
                }
                JanusEvent.HANGUP.value() -> {
                    // Registering
                    val hangUpEvent = json.decodeFromString<HangUpEvent>(message)
                    callback(JanusEvent.HANGUP,hangUpEvent)
                }
                JanusEvent.REGISTERED.value() -> {
                    //callback(JanusMethod.EVENT,success)
                    val registerSuccess = json.decodeFromString<RegisterSuccess>(message)
                    callback(JanusEvent.REGISTERED,registerSuccess)
                }
                JanusEvent.INCOMING_CALL.value() -> {
                    val incomingCall = json.decodeFromString<JanusCallEvent>(message)
                    callback(JanusEvent.INCOMING_CALL,incomingCall)
                }
                JanusEvent.RINGING.value() -> {
                    val ringing = json.decodeFromString<JanusCallEvent>(message)
                    callback(JanusEvent.RINGING,ringing)
                }
                JanusEvent.ACCEPTED.value() -> {
                    Logger.i { "Accepted" }
                    val answered = json.decodeFromString<JanusCallEvent>(message)
                    callback(JanusEvent.ACCEPTED,answered)
                }
                else -> {
                    Logger.i { "Event not found $message" }
                }
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
            val result = json.decodeFromString<Ack>(message)
            callback(JanusEvent.ACKNOWLEDGED,result)
        }
    }
}





