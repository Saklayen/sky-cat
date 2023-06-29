package com.gs.skycatnews.utils

import android.annotation.SuppressLint
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gs.skycatnews.R
import com.gs.skycatnews.ui.weblink.WebLinkViewModel
import com.gs.skycatnews.ui.weblink.WebPageLoadingStatus
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.periodUntil
import timber.log.Timber


@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(url)
        .placeholder(R.drawable.placeholder)
        .into(view)
}

@BindingAdapter("bindTimeLine")
fun bindTimeLine(view: TextView, timeLine: String?) {
    timeLine?.let {
        try {
            val targetUtcTime = Instant.parse(timeLine)
            Timber.e("$targetUtcTime")
            val calculatedTimeDiff = calculateTimeDifference(targetUtcTime)
            view.text = calculatedTimeDiff
        } catch (e: Exception) {
            Timber.e("Exception: ${e.message}")
        }

    }
}

fun calculateTimeDifference(targetUtcTime: Instant): String {
    val currentUtcTime = Clock.System.now()
    val period = targetUtcTime.periodUntil(currentUtcTime, TimeZone.UTC)
    return when {
        period.years > 0 -> "${period.years} years ago"
        period.months > 0 -> "${period.months} months ago"
        period.days > 1 -> "${period.days} days ago"
        period.days == 1 -> "yesterday"
        period.hours > 0 -> "${period.hours} hours ago"
        period.minutes > 0 -> "${period.minutes} hours ago"
        else -> "just now"
    }
}

@SuppressLint("SetJavaScriptEnabled")
@BindingAdapter("initializeWebView")
fun initializeWebView(webView: WebView, webLinkViewModel: WebLinkViewModel?) {
    webLinkViewModel?.let { viewModel ->
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            allowFileAccess = true
            setSupportZoom(false)
            textZoom = 100
            mediaPlaybackRequiresUserGesture = false
        }
        webView.webViewClient = viewModel.webViewClient
    }
}

@BindingAdapter("showLoadingProgress")
fun showLoadingProgress(view: View, loadingStatus: WebPageLoadingStatus?) {
    when (loadingStatus) {
        WebPageLoadingStatus.LOADING -> view.visibility = View.VISIBLE
        else -> view.visibility = View.GONE
    }
}

@BindingAdapter("loadUrl")
fun loadUrl(webView: WebView, url: String?) {
    url?.let {
        Timber.d("new URL to load: $url")
        webView.visibility = View.GONE
        webView.stopLoading()
        webView.clearCache(true)
        webView.loadUrl(it)
        webView.visibility = View.VISIBLE
    }
}
