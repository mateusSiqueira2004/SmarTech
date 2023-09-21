package com.example.smartechnavigationdrawer.navigation.ui.exit

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.smartechnavigationdrawer.Login
import com.example.smartechnavigationdrawer.R
import com.example.smartechnavigationdrawer.databinding.FragmentExitBinding
import com.google.firebase.auth.FirebaseAuth

class ExitFragment : Fragment() {

    private lateinit var binding: FragmentExitBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExitBinding.inflate(inflater,  container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(activity, Login::class.java))
        activity?.finish()
    }
}