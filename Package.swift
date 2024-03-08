// swift-tools-version:5.3
import PackageDescription

// BEGIN KMMBRIDGE VARIABLES BLOCK (do not edit)
let remoteKotlinUrl = "https://maven.pkg.github.com/team-telnyx/janus-message-sdk/JanusMessageSdk/shared-kmmbridge/0.7.0/shared-kmmbridge-0.7.0.zip"
let remoteKotlinChecksum = "6a9e0ec1954ebf3c51bb905ba59bef491e87f5645f41d0513c594c8eb7ef920a"
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