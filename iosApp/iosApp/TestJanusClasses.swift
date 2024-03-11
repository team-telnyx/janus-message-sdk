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
    let createTransaction = CreateTransaction(janus: "kk", transaction: "String")
    //let result = try! JanusUtils().encode(cls: createTransaction)
    
    print("Transaction => "  + createTransaction.default().transaction)
    //JanusUtils.encode(createTransaction.default())

    
}
