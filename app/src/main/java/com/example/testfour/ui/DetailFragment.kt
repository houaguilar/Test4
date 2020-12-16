package com.example.testfour.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.testfour.R
import com.example.testfour.data.model.Story
import com.example.testfour.databinding.FragmentDetailBinding
import com.example.testfour.databinding.FragmentStoriesBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var story: Story

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().let {
            DetailFragmentArgs.fromBundle(it).also { args ->
                story = args.story
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailBinding.bind(view)

        story.url?.let { binding.webView.loadUrl(it) }
        binding.webView.webViewClient = object: WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }
        }
    }
}