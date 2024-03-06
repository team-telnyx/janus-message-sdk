package com.telnyx.janusmessagesdk

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform