# CMP Bottom Bar

CMP Bottom Bar is a Compose Multiplatform library containing a shared bottom-bar model and several visual styles. It targets Android, iOS, desktop, and WebAssembly.

## Modules

| Module | Purpose |
| --- | --- |
| `coreBottomBar` | Shared item models, listeners, indicator configuration, and helpers |
| `basicBottomBar` | Horizontal and vertical basic bottom bars |
| `tinyGlideBottomBar` | Animated bar with expandable sub-items |
| `aztopiaBottomBar` | Animated circular Aztopia style |
| `expandableBottomBar` | Expandable bottom-bar experiment |
| `gooeyBottomBar` | Gooey/blurred bottom-bar experiment |
| `composeApp` | Multiplatform demo application |
| `iosApp` | SwiftUI host for the iOS demo |

Style modules depend on `coreBottomBar`; the demo app brings the styles together.

## Published artifacts

The currently configured Maven Central artifacts are:

```kotlin
implementation("io.github.mejdi14:cmp-bottombar-core:0.4.3")
implementation("io.github.mejdi14:cmp-bottombar-basic:0.4.3")
implementation("io.github.mejdi14:cmp-bottombar-tinyGlide:0.4.3")
implementation("io.github.mejdi14:cmp-bottombar-aztopia:0.4.3")
```

`expandableBottomBar` and `gooeyBottomBar` are included as local experimental modules and are not currently configured for publication.

## Build the project

Use JDK 17 or 21, then run:

```shell
./gradlew :composeApp:assembleDebug
./gradlew :composeApp:run
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
```

On macOS, open `iosApp/iosApp.xcodeproj` to run the iOS demo.

Publishing is handled by `.github/workflows/publish.yml`. Maven Central credentials and the GPG private key must remain in GitHub Actions secrets; they must never be added to `gradle.properties` or committed files.

## License

Licensed under the Apache License 2.0. See [LICENSE](LICENSE).
