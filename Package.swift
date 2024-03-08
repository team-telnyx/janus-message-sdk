// swift-tools-version:5.3
import PackageDescription

// BEGIN KMMBRIDGE VARIABLES BLOCK (do not edit)
let remoteKotlinUrl = "https://maven.pkg.github.com/team-telnyx/janus-message-sdk/JanusMessageSdk/shared-kmmbridge/0.7.1/shared-kmmbridge-0.7.1.zip"
let remoteKotlinChecksum = "dd7e8479ef74fdde0627dcd7f6ccb50bdfac4275d280f1cbdb30717a5f17e0e6"
let packageName = "shared"
// END KMMBRIDGE BLOCK

let package = Package(
    name: packageName,
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: packageName,
            targets: [packageName]
        ),
    ],
    targets: [
        .binaryTarget(
            name: packageName,
            url: remoteKotlinUrl,
            checksum: remoteKotlinChecksum
        )
        ,
    ]
)