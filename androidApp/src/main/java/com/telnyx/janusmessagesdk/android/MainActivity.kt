package com.telnyx.janusmessagesdk.android

import android.os.Bundle
import com.telnyx.janusmessagesdk.janus.JanusEvent
import com.telnyx.janusmessagesdk.janus.RegisterSuccess
import com.telnyx.janusmessagesdk.janus.decodeJanusMessage


fun main() {

    decodeJanusMessage("{\n" +
            "  \"janus\": \"event\",\n" +
            "  \"plugindata\": {\n" +
            "    \"data\": {\n" +
            "      \"result\": {\n" +
            "        \"event\": \"registered\",\n" +
            "        \"headers\": {},\n" +
            "        \"master_id\": 2656454203,\n" +
            "        \"register_sent\": true,\n" +
            "        \"username\": \"isaac28055\"\n" +
            "      },\n" +
            "      \"sip\": \"event\"\n" +
            "    },\n" +
            "    \"plugin\": \"janus.plugin.sip\"\n" +
            "  },\n" +
            "  \"sender\": 485245946375374,\n" +
            "  \"session_id\": 2496119861992003\n" +
            "}"){ event,data ->
        if (event == JanusEvent.REGISTERED){
           val suc = (data as RegisterSuccess)
            println(suc.plugindata?.data)
        }
    }
}



