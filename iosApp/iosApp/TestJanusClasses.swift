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
    JanusKt.decodeJanusMessage(message: "") { method, base in
        method == JanusMethod.attach
        base as! CreateTransaction
    }
    print("Transaction => "  + createTransaction.encode() )
    
    //JanusUtils.encode(createTransaction.default())

    
}
