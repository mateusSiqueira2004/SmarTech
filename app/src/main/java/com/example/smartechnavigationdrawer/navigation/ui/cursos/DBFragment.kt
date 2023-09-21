package com.example.smartechnavigationdrawer.navigation.ui.cursos


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smartechnavigationdrawer.R
import com.example.smartechnavigationdrawer.databinding.FragmentDBBinding


class DBFragment : Fragment(R.layout.fragment_d_b) {
    private lateinit var binding: FragmentDBBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            binding.imgFB.setOnClickListener {
                findNavController().navigate(R.id.action_nav_curso_catg_db_to_nav_lista_videos)
            }
            binding.imgMySql.setOnClickListener {
                findNavController().navigate(R.id.action_nav_curso_catg_db_to_nav_lista_videos)
            }
            binding.imgSqlite.setOnClickListener {
                findNavController().navigate(R.id.action_nav_curso_catg_db_to_nav_lista_videos)
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDBBinding.inflate(inflater,container,false)
        return binding.root
    }
}