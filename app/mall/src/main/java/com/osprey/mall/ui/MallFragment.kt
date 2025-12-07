package com.osprey.mall.ui

import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebChromeClient
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import com.osprey.mall.R
import com.osprey.core.base.BaseFragment
import com.osprey.mall.databinding.FragmentMallLayoutBinding

@AndroidEntryPoint
class MallFragment : BaseFragment<FragmentMallLayoutBinding, MallViewModel>(
    MallViewModel::class) {

    override fun layoutId(): Int = R.layout.fragment_mall_layout

    override fun initialize() {
        setupUI()
        setupAction()
        observe()
    }

    private fun setupUI() {
        binding.lifecycleOwner = this
        setupWebView()
    }

    private fun setupWebView() {
        binding.webView.apply {
            settings.javaScriptEnabled = true

            settings.domStorageEnabled = true

            settings.builtInZoomControls = true
            settings.displayZoomControls = false

            settings.loadsImagesAutomatically = true

            settings.userAgentString = settings.userAgentString

            settings.cacheMode = android.webkit.WebSettings.LOAD_DEFAULT

            settings.setSupportMultipleWindows(false)

            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    binding.progressBar.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    // Hide progress bar
                    binding.progressBar.visibility = View.GONE
                }

                override fun shouldOverrideUrlLoading(view: WebView?, request: android.webkit.WebResourceRequest?): Boolean {
                    // Handle URL loading inside WebView
                    return false
                }
            }

            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                }
            }

            loadUrl("https://osprey.life/")
        }
    }

    private fun setupAction() {
        binding.apply {
            // Handle back button press for WebView navigation
        }
    }

    private fun observe() {
        // Observe ViewModel data if needed
    }

    fun onBackPressed(): Boolean {
        return if (binding.webView.canGoBack()) {
            binding.webView.goBack()
            true
        } else {
            false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.webView.apply {
            clearHistory()
            clearCache(true)
            destroy()
        }
    }

    companion object {
        fun newInstance() = MallFragment()
    }
}