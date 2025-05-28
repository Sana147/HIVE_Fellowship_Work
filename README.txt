# CVE-2020-16010 Educational Proof of Concept (HTML + Kotlin Version)

This is a safe, educational PoC demonstrating how malformed images may impact rendering behavior in Android WebView. It simulates crash-prone behavior related to CVE-2020-16010.

## Contents

- `MainActivity.kt` — Kotlin WebView loading code
- `generate_malformed_png.py` — Generates a malformed PNG
- `malformed.png` — Malformed image file (generate using script)

## How to Use (Windows)

1. Install Android Studio and create a new **Kotlin** project.
2. Replace your `MainActivity.kt` with the one in this archive.
3. Add INTERNET permission to `AndroidManifest.xml`and network_security_config.xml file. The directions are given in the report.
4. Run `generate_malformed_png.py` using Python 3 to create `malformed.png`.
5. Run `python server.py` to host the image.
6. Run the app in an emulator. The image is loaded via WebView. Rigth now, it does not simulate a crash scenario due to the use of patched Chrome version but it should work on unpatched versions of Chrome.

Use `10.0.2.2` to access localhost from the Android emulator.
