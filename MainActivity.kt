package com.example.cve202016010demo

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView = WebView(this)
        setContentView(webView)

        // Enable JavaScript
        webView.settings.javaScriptEnabled = true

        // Allow mixed content (HTTP content on HTTPS page), needed if your page is HTTPS but image is HTTP
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        // Set WebViewClient to handle page navigation within the WebView
        webView.webViewClient = WebViewClient()

        // Set WebChromeClient to capture console.log from web content
        webView.webChromeClient = object : WebChromeClient() {
            override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
                Log.d(
                    "WEBVIEW",
                    "${consoleMessage.message()} -- From line ${consoleMessage.lineNumber()} of ${consoleMessage.sourceId()}"
                )
                return true
            }
        }

        // HTML content with an image loaded from local HTTP server on host machine
        val html = """
    <html>
    <body>
        <h2>WebView Image Test</h2>
        <p>This is a malformed PNG image loaded from a local HTTP server.</p>
        <img src='http://10.0.2.2:8000/malformed.png' width='200' height='200' 
             onerror="console.log('Image failed to load')" />
    </body>
    </html>
"""
        // Load the HTML with base URL to allow relative resource loading and proper origin for CORS
        webView.loadDataWithBaseURL("http://10.0.2.2:8000/", html, "text/html", "UTF-8", null)
    }
}
