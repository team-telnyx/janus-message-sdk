package com.telnyx.janusmessagesdk

import com.telnyx.janusmessagesdk.janus.RegisterSuccess
import kotlinx.serialization.json.Json
import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidGreetingTest {

    @Test
    fun testisLenient() {
        val js = "{\"janus\":\"event\",\"plugindata\":{\"data\":{\"result\":{\"event\":\"registered\",\"headers\":{},\"master_id\":2622431486,\"register_sent\":true,\"username\":\"isaac89943\"},\"sip\":\"event\"},\"plugin\":\"janus.plugin.sip\"},\"sender\":2298617662845409,\"session_id\":5588861793971094}"
        val json = Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
            isLenient = true
        }
       val rg =  json.decodeFromString<RegisterSuccess>(js)

        assertTrue(rg.sender == 2298617662845409L)
    }

}