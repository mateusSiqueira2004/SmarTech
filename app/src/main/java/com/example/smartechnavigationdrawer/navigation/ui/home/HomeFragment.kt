package com.example.smartechnavigationdrawer.navigation.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smartechnavigationdrawer.R
import com.example.smartechnavigationdrawer.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        Log.e("MINHA NOOOSSSA DEUX","SOCOOOOOOORRROOOOOO EU NAO AGUENTO MAIS")
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAdm.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_nav_geruser)
        }
    }

    override fun onStart() {
        super.onStart()
        var comparacao = FirebaseAuth.getInstance().currentUser?.email
        var retorno = "@"
        var listar = comparacao
            ?.split(retorno)
            ?.takeLast(1)

        var adm = "[smartech.corp]"
        var btnAdm = view?.findViewById<Button>(R.id.btnAdm)

        if(listar.toString() == adm){
            btnAdm?.visibility = View.VISIBLE
        }else{
            btnAdm?.visibility = View.GONE
        }
    }
}