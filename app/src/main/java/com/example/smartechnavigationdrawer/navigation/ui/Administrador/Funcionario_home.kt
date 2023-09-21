package com.example.smartechnavigationdrawer.navigation.ui.Administrador

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smartechnavigationdrawer.R
import com.example.smartechnavigationdrawer.databinding.FragmentFuncionarioHomeBinding

class funcionario_home : Fragment() {

  private lateinit var binding: FragmentFuncionarioHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFuncionarioHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.funCadastro.setOnClickListener {
           findNavController().navigate(R.id.action_funcionario_home_to_cadastrar_adm)
        }
        binding.funExcluir.setOnClickListener {
            findNavController().navigate(R.id.action_funcionario_home_to_Excluir_Funcioanrio)
        }
    }
}