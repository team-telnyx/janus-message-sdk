package com.telnyx.janusmessagesdk.janus

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.KClass

enum class Janus(val value: String)  {

    CREATE("create"),
    KEEP_ALIVE("keepalive"),
    SUCCESS("success"),

    MESSAGE("message"),
    EVENT("event"),
    ATTACH("attach")
}

