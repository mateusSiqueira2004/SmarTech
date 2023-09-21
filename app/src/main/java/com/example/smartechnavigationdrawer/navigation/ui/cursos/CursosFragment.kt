package com.example.smartechnavigationdrawer.navigation.ui.cursos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smartechnavigationdrawer.R
import com.example.smartechnavigationdrawer.databinding.FragmentCursoBinding

class CursosFragment : Fragment() {

    private lateinit var _binding: FragmentCursoBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            _binding.imgLP.setOnClickListener {
                findNavController().navigate(R.id.action_nav_curso_to_nav_curso_catg_lp2)
            }
            _binding.imgHardware.setOnClickListener {
                findNavController().navigate(R.id.action_nav_curso_to_nav_curso_catg_hd)
            }
            _binding.imgDB.setOnClickListener {
                findNavController().navigate(R.id.action_nav_curso_to_nav_curso_catg_db)
            }
    }



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCursoBinding.inflate(inflater, container, false)
        return _binding.root


    }

}