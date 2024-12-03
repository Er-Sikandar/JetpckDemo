package com.example.newjetpackapp.activity

import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.newjetpackapp.component.appBar
import com.example.newjetpackapp.utils.CallFun.showLog

@Composable
fun WebViewScreen(onBackToHome: () -> Unit, url: String, type: Int) {
    val context: Context = LocalContext.current
    var progress by remember { mutableStateOf(0) }
    var isLoading by remember { mutableStateOf(true) }
    var canGoBack by remember { mutableStateOf(false) }
    var webView: WebView? = null
    showLog("WebView", "URL: $url and Type: $type")

    Scaffold(
        topBar = {
            appBar("Web Sites", onBackToHome, false, Icons.Filled.Info, onActClick = {
                Toast.makeText(context, "Clicked: Act", Toast.LENGTH_SHORT).show()
            })
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column {
                if (isLoading) {
                    LinearProgressIndicator(
                        progress = progress / 100f,
                        modifier = Modifier.fillMaxWidth(), Color.Red
                    )
                }
                AndroidView(factory = { context ->
                    WebView(context).apply {
                        webViewClient = object : WebViewClient() {
                            override fun onPageFinished(view: WebView?, url: String?) {
                                super.onPageFinished(view, url)
                                  isLoading = false
                            }
                        }
                        webChromeClient = object : WebChromeClient() {
                            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                                progress = newProgress
                                isLoading = newProgress < 100
                            }
                        }
                        webViewClient = object : WebViewClient() {
                            override fun onReceivedError(
                                view: WebView?,
                                request: WebResourceRequest?,
                                error: WebResourceError?
                            ) {
                                super.onReceivedError(view, request, error)
                                showLog(
                                    "WebViewError",
                                    "Error: ${error?.description} on URL: ${request?.url}"
                                )
                            }

                            override fun onReceivedHttpError(
                                view: WebView?,
                                request: WebResourceRequest?,
                                errorResponse: WebResourceResponse?
                            ) {
                                super.onReceivedHttpError(view, request, errorResponse)
                                showLog(
                                    "WebViewHttpError",
                                    "HTTP error ${errorResponse?.statusCode} on URL: ${request?.url}"
                                )
                            }
                        }
                        settings.javaScriptEnabled = true
                        settings.domStorageEnabled = true
                        settings.useWideViewPort = true
                        settings.setSupportZoom(true)
                        settings.setAllowFileAccessFromFileURLs(true)
                        settings.setAllowUniversalAccessFromFileURLs(true)
                        settings.builtInZoomControls = true
                        settings.displayZoomControls = false
                        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)
                        showLog("TAG", "WebView Init: ${url}")
                        if (type == 1) {
                            loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=$url")
                        } else {
                            loadUrl(url)
                        }
                        setOnKeyListener { _, keyCode, event ->
                            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
                                if (canGoBack()) {
                                    goBack()
                                    true
                                } else {
                                    onBackToHome()
                                    true
                                }
                            } else {
                                false
                            }
                        }
                    }.also {
                        webView = it
                    }
                }, update = { webView ->
                    showLog("TAG", "Update: ${webView.url}")
                    canGoBack = webView.canGoBack()
                    webView.loadUrl(webView.url!!)
                })
            }

        }
    }

    BackHandler {
        if (webView?.canGoBack() == true) {
            webView?.goBack()
        } else {
            onBackToHome()
        }
    }
}