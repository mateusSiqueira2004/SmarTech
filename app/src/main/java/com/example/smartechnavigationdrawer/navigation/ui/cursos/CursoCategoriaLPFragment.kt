package com.example.smartechnavigationdrawer.navigation.ui.cursos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smartechnavigationdrawer.R
import com.example.smartechnavigationdrawer.databinding.ActivityMainBinding
import com.example.smartechnavigationdrawer.databinding.FragmentCursoCategoriaLPBinding


class CursoCategoriaLPFragment : Fragment(R.layout.fragment_curso_categoria_l_p) {
    private lateinit var binding: FragmentCursoCategoriaLPBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCursoCategoriaLPBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgCss.setOnClickListener{
                findNavController().navigate(R.id.action_nav_curso_catg_lp_to_nav_lista_videos)
            }
        binding.imgHtml.setOnClickListener{
                findNavController().navigate(R.id.action_nav_curso_catg_lp_to_nav_lista_videos)
            }
        binding.imgJS.setOnClickListener{
                findNavController().navigate(R.id.action_nav_curso_catg_lp_to_nav_lista_videos)
            }
        binding.imgJava.setOnClickListener{
            findNavController().navigate(R.id.action_nav_curso_catg_lp_to_nav_lista_videos)
        }
    }
}