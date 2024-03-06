package com.telnyx.janusmessagesdk.janus

enum class Janus(val value: String) {
    CREATE("create"),
    KEEP_ALIVE("keepalive"),
    SUCCESS("success"),
    ATTACH("attach"),
    MESSAGE("message"),
    EVENT("event"),
}
