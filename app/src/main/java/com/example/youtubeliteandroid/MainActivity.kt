package com.example.youtubeliteandroid

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var customView: FrameLayout
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webview)
        customView = findViewById(R.id.customView)

        webView.webChromeClient = object: MyWebChromeClient(webView, customView){}
        webView.loadUrl("https://youtube-lite.js.org/")

        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true

        val newUA =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36"
        webView.settings.userAgentString = newUA
    }
}

open class MyWebChromeClient(private val webView:WebView, private val customView:FrameLayout) : WebChromeClient() {
    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
        super.onShowCustomView(view, callback)
        webView.visibility = View.GONE
        customView.visibility = View.VISIBLE
        customView.addView(view)
    }

    override fun onHideCustomView() {
        super.onHideCustomView()
        webView.visibility = View.VISIBLE
        customView.visibility = View.GONE
    }
}
