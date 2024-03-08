// swift-tools-version:5.3
import PackageDescription

// BEGIN KMMBRIDGE VARIABLES BLOCK (do not edit)
let remoteKotlinUrl = "https://maven.pkg.github.com/team-telnyx/janus-message-sdk/JanusMessageSdk/shared-kmmbridge/0.7.2/shared-kmmbridge-0.7.2.zip"
let remoteKotlinChecksum = "7a2e2b2d6120e843ed6ba8fd3d548d96064e900c34c062983b29412718483302"
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