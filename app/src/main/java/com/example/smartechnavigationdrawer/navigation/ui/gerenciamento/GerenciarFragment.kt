package com.example.smartechnavigationdrawer.navigation.ui.gerenciamento

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.smartechnavigationdrawer.R
import com.example.smartechnavigationdrawer.databinding.FragmentGerenciarBinding


class GerenciarFragment : Fragment() {
   lateinit var binding: FragmentGerenciarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentGerenciarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnFuncionario.setOnClickListener {
            findNavController().navigate(R.id.action_nav_geruser_to_funcionario_home)
        }
    }
}