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
| `figmaBottomBar` | Compact grouped toolbar inspired by Figma |
| `composeApp` | Multiplatform demo application |
| `iosApp` | SwiftUI host for the iOS demo |

Style modules depend on `coreBottomBar`; the demo app brings the styles together.

## Core API

The development API keeps immutable item configuration separate from composable state:

- `BottomBarItem` contains only visuals shared by selectable items.
- `BottomBarInteraction` defines whether an item is enabled and whether selection should select, toggle, or remain unchanged.
- `BottomBarClickListener<T>` and `BottomBarHoverListener<T>` provide typed callbacks without unsafe casts or empty listener objects.
- `BottomBarItemGroup<T>` keeps reusable group boundaries separate from style-specific divider rendering.
- Selection indexes and selected state are owned by the rendering composable, not stored in item models.
- Style-specific configuration, such as Basic bar additional items and hover text styling, lives in its corresponding style module.

This API is a breaking change intended for the next `0.5.x` release line.

## Basic bar API

The Basic style exposes state explicitly and supports horizontal or vertical placement:

```kotlin
val state = rememberBasicBarState(initialSelectedIndex = 0)

BasicBottomBar(
    items = items,
    state = state,
    config = BasicBarConfig(position = BasicBarPosition.HorizontalBottom),
) { item, index ->
    // `index` is null for an optional start or end action.
}
```

`BasicBarState.select(null)` clears selection. Item interaction modes are respected, including toggle and non-selecting actions. Empty item lists render safely.

## Published artifacts

The currently configured Maven Central artifacts are:

```kotlin
implementation("io.github.mejdi14:cmp-bottombar-core:0.4.3")
implementation("io.github.mejdi14:cmp-bottombar-basic:0.4.3")
implementation("io.github.mejdi14:cmp-bottombar-tinyGlide:0.4.3")
implementation("io.github.mejdi14:cmp-bottombar-aztopia:0.4.3")
```

`expandableBottomBar` and `gooeyBottomBar` are included as local experimental modules and are not currently configured for publication.

## Figma attribution

The `figmaBottomBar` style is independently implemented and inspired by the toolbar interface of [Figma](https://www.figma.com/). Figma is a trademark of Figma, Inc. This project is not affiliated with or endorsed by Figma.

## Build the project

The development toolchain currently uses:

- Gradle 8.14.5
- Kotlin 2.4.10
- Compose Multiplatform 1.11.1
- Android Gradle Plugin 8.13.2
- Android compile/target SDK 36

Use JDK 17 or 21, then run:

```shell
./gradlew :composeApp:assembleDebug
./gradlew :composeApp:run
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
```

On macOS, open `iosApp/iosApp.xcodeproj` to run the iOS demo.

Supported Apple targets are iOS ARM64 devices and Apple Silicon simulators. The obsolete Apple x86_64 target was removed because current Compose Multiplatform releases no longer publish it.

Compose Multiplatform 1.12 requires API 37 and the AGP 9 Android-KMP architecture. That upgrade is intentionally deferred until the Android application can be extracted from the shared multiplatform module.

Publishing is handled by `.github/workflows/publish.yml`. Maven Central credentials and the GPG private key must remain in GitHub Actions secrets; they must never be added to `gradle.properties` or committed files.

## License

Licensed under the Apache License 2.0. See [LICENSE](LICENSE).
