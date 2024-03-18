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
    //CreateTransaction(janus: String, transaction: T##String)
    let createTransaction = CreateTransaction(transaction: "").default()
    //let result = try! JanusUtils().encode(cls: createTransaction)
    let  body = CallBody().default(userName: "Isaac23323")

    
    print("Transaction => " + body.encode())
    
    let callBody = CallBody().default(userName: "isaac1212")
    let call = Call(body: callBody, jsep: Jsep(sdp: "", type: "offer"))
    
    //JanusUtils.encode(createTransaction.default())

    
}
