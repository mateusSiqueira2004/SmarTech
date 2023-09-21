package com.example.smartechnavigationdrawer.navigation.ui.perfil

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.set
import androidx.navigation.fragment.findNavController
import com.example.smartechnavigationdrawer.R
import com.example.smartechnavigationdrawer.databinding.FragmentEditarUsusarioBinding
import com.example.smartechnavigationdrawer.databinding.FragmentPerfilBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Editar_ususario : Fragment() {
    private lateinit var binding: FragmentEditarUsusarioBinding
    val userid = FirebaseAuth.getInstance().currentUser?.uid
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditarUsusarioBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        puxarDados()

        binding.btnEditar.setOnClickListener{
            salvarEdit()
            findNavController().navigate(R.id.nav_home)
        }



    }
    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
    private fun puxarDados(){
        db.collection("Usuários").document(userid.toString()).addSnapshotListener { documento, erro ->
            if(documento != null){
                val nome= documento.getString("nome")
                val tele = documento.getString("telefone")
                val email = documento.getString("email")

                binding.editName.text = nome!!.toEditable()
                binding.EdiTele.text = tele!!.toEditable()

            }
        }
    }
    private fun salvarEdit(){
        db.collection("Usuários").document(userid.toString()).addSnapshotListener { documento, erro ->
            if(documento != null){
                val nome= documento.getString("nome")
                val tele = documento.getString("telefone")
                val email = documento.getString("email")
            }

        val usuariosMap = hashMapOf(
            "nome" to binding.editName.text.toString(),
            "telefone" to binding.EdiTele.text.toString(),
            "email" to documento?.getString("email")

        )

        db.collection("Usuários").document(userid.toString()).set(usuariosMap).addOnCompleteListener {
            Log.d("db", "sucesso ao salvar os dados do usuario")
            Toast.makeText(context, "Informações salvas com sucesso", Toast.LENGTH_SHORT).show()


            }
        }
    }
}
