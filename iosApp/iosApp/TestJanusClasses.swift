//
//  TestJanusClasses.swift
//  iosApp
//
//  Created by Isaac Akakpo on 05/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import JanusMessageSDK

func testJanus(){
    //CreateTransaction(janus: String, transaction: <#T##String#>)
    let createTransaction = CreateTransaction(transaction: "").default()
    //let result = try! JanusUtils().encode(cls: createTransaction)
    JanusKt.decodeJanusMessage(message: "{\n" +
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
                               "}") { method, base in
        if(method == JanusEvent.registered){
            var mom = base as! RegisterSuccess
            print("Transaction => \(mom.sessionId)"  )

        }
        print("method : \(method)")
    }
    print("Transaction => "  + createTransaction.encode() )
    
    //JanusUtils.encode(createTransaction.default())

    
}
