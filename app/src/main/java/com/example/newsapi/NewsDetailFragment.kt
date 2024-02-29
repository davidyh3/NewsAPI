package com.example.newsapi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.newsapi.databinding.FragmentNewsDetailBinding

class NewsDetailFragment : Fragment() {

    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = checkNotNull(_binding) {
        "Cannot access binding because it is null."
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString("title")
        val description = arguments?.getString("description")
        val content = arguments?.getString("content")
        val imageUrl = arguments?.getString("imageUrl")

        // Now you can use these parameters to update your UI
        title?.let { binding.titleTextView.text = it }
        description?.let { binding.descriptionTextView.text = it }
        content?.let { binding.contentTextView.text = it }
        imageUrl?.let {
            Glide.with(this)
                .load(it)
                .placeholder(R.drawable.placeholder)
                .into(binding.newsImageView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
