package com.adamg.hnreader.views.webview


import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.adamg.hnreader.AppConstants
import com.adamg.hnreader.R
import com.adamg.hnreader.views.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_web_view.*


/**
 * A simple [Fragment] subclass.
 */
class WebViewFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeLayout.setOnRefreshListener(this)
        configureWebView()
        loadWebFormUrl()
    }

    private fun configureWebView() {
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                swipeLayout?.isRefreshing = true
            }

            override fun onPageFinished(view: WebView?, url: String) {
                super.onPageFinished(view, url)
                swipeLayout?.isRefreshing = false
                if (url.endsWith(".pdf")) {
                    webView?.zoomIn()
                }
            }
        }

        webView.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN &&  keyCode == KeyEvent.KEYCODE_BACK) {
                if (webView.canGoBack()) {
                    webView.goBack()
                    return@OnKeyListener true
                }
            }
            false
        })

        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false
        webView.settings.useWideViewPort = true
        webView.settings.javaScriptEnabled = true
        webView.settings.allowContentAccess = false
        webView.settings.allowFileAccess = false
        webView.settings.allowFileAccessFromFileURLs = false
        webView.settings.allowUniversalAccessFromFileURLs = false
    }

    private fun loadWebFormUrl() {
        val itemUrl = arguments.getString(AppConstants.ITEM_URL)
        if (itemUrl.endsWith(".pdf")) {
            webView.loadUrl("https://docs.google.com/viewer?url=" + itemUrl)
        } else {
            webView.loadUrl(itemUrl)
        }
    }

    override fun onRefresh() {
        webView.reload()
    }

    companion object Factory {
        fun create(itemUrl: String?): WebViewFragment {
            val webViewFragment = WebViewFragment()
            val bundle = Bundle()
            bundle.putString(AppConstants.ITEM_URL, itemUrl)
            webViewFragment.arguments = bundle
            return webViewFragment
        }
    }

    override fun injectDependencies() {
    }
}
