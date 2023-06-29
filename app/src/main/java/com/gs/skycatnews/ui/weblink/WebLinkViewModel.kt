package com.gs.skycatnews.ui.weblink

import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WebLinkViewModel @Inject constructor() : ViewModel() {

    private val _currentUrl = MutableStateFlow("")
    val currentUrl: StateFlow<String> = _currentUrl

    private val _webPageLoadingStatus = MutableStateFlow(WebPageLoadingStatus.UNKNOWN)
    val webPageLoadingStatus: StateFlow<WebPageLoadingStatus> = _webPageLoadingStatus

    val webViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView, url: String) {
            _webPageLoadingStatus.value = WebPageLoadingStatus.FINISHED

        }

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?,
        ) {
            super.onReceivedError(view, request, error)
            Timber.e("Url load error: $error")
            _webPageLoadingStatus.value = WebPageLoadingStatus.ERROR

        }

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?,
        ): Boolean {
            return false
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            _webPageLoadingStatus.value = WebPageLoadingStatus.LOADING
        }
    }

    fun loadUrl(url: String) {
        _currentUrl.value = url
    }
}
