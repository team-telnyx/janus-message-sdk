//
//  iosAppTests.swift
//  iosAppTests
//
//  Created by Isaac Akakpo on 13/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import XCTest
import JanusMessageSDK

final class iosAppTests: XCTestCase {

    override func setUpWithError() throws {
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }

    override func tearDownWithError() throws {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }
    
    func testRegisterCastFromKt(){
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
                let mom = base as! RegisterSuccess
                print("Transaction => \(mom.sessionId)"  )

            }
            print("method : \(method)")
        }
    }

    func testExample() throws {
        // This is an example of a functional test case.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
        // Any test you write for XCTest can be annotated as throws and async.
        // Mark your test throws to produce an unexpected failure when your test encounters an uncaught error.
        // Mark your test async to allow awaiting for asynchronous code to complete. Check the results with assertions afterwards.
    }

    func testPerformanceExample() throws {
        // This is an example of a performance test case.
        measure {
            // Put the code you want to measure the time of here.
        }
    }

}
