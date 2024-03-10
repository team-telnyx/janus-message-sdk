// swift-tools-version:5.3
import PackageDescription

// BEGIN KMMBRIDGE VARIABLES BLOCK (do not edit)
let remoteKotlinUrl = "file:/Users/isaacakakpo/.m2/repository//JanusMessageSdk/shared-kmmbridge/0.7.3/shared-kmmbridge-0.7.3.zip"
let remoteKotlinChecksum = "f71fe465485bfbd62a2bcc7eb68b49f3c3f9a3b0167ade4dee4acbb891ad9415"
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