package com.example.smartechnavigationdrawer.navigation.ui.comunidade

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import com.example.smartechnavigationdrawer.R
import com.example.smartechnavigationdrawer.databinding.FragmentComunidadeBinding
import com.google.api.AnnotationsProto.http


class ComunidadeFragment : Fragment() {

    private lateinit var binding: FragmentComunidadeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComunidadeBinding.inflate(inflater, container, false)
        return binding.root
    }
}