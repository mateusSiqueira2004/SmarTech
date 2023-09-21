package com.example.smartechnavigationdrawer.navigation.ui.Administrador

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.smartechnavigationdrawer.Login
import com.example.smartechnavigationdrawer.R
import com.example.smartechnavigationdrawer.databinding.FragmentEditarUsusarioBinding
import com.example.smartechnavigationdrawer.databinding.FragmentExcluirFuncionarioBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.ktx.Firebase
import kotlin.contracts.Returns

class Excluir_Funcionario : Fragment() {

    private lateinit var binding: FragmentExcluirFuncionarioBinding

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExcluirFuncionarioBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnExcluirFun.setOnClickListener {
            deletar()
        }
    }
    private fun deletar(){
        val userid = FirebaseAuth.getInstance().currentUser?.uid
        var senha =  binding.SenhaAdm.text.toString()
        var confsenha = binding.ConfSenhaAdm.text.toString()
        var email = binding.ediEmailFun.text.toString()
        if(senha == confsenha) {
            auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener { autenticacao ->
                if (autenticacao.isSuccessful) {
                    db.collection("Funcionario").document(userid.toString()).delete().addOnSuccessListener {
                        Toast.makeText(context, "Dados excluido com sucesso!", Toast.LENGTH_SHORT).show()
                        }
                    auth.currentUser!!.delete()
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(activity, Login::class.java))
                    activity?.finish()
                }
            }.addOnFailureListener { exception ->
                val menssageErro = when (exception) {
                    is FirebaseAuthWeakPasswordException -> "Senha incorreta!"
                    is FirebaseAuthInvalidCredentialsException -> "Digite um email valido!"
                    is FirebaseNetworkException -> "Sem conexão com a internet!"
                    else -> "Erro ao excluir o usuario!"
                }
                Toast.makeText(context, menssageErro, Toast.LENGTH_LONG).show()

            }
        }
    }
}




