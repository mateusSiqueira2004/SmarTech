package com.example.smartechnavigationdrawer.navigation.ui.cursos

import android.app.UiModeManager.MODE_NIGHT_YES
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.getDefaultNightMode
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smartechnavigationdrawer.R
import com.example.smartechnavigationdrawer.databinding.FragmentHDBinding

class HDFragment : Fragment(R.layout.fragment_h_d) {

    private lateinit var binding: FragmentHDBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            binding.imgMCP.setOnClickListener{
                findNavController().navigate(R.id.action_nav_curso_catg_hd_to_nav_lista_videos)
            }
            binding.imgRede.setOnClickListener {
               findNavController().navigate(R.id.action_nav_curso_catg_hd_to_nav_lista_videos)
            }
            binding.imgServer.setOnClickListener {
               findNavController().navigate(R.id.action_nav_curso_catg_hd_to_nav_lista_videos)
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHDBinding.inflate(inflater, container, false)
        return binding.root
    }
}


