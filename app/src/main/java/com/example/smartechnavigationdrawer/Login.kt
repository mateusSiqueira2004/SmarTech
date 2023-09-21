package com.example.smartechnavigationdrawer

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smartechnavigationdrawer.databinding.ActivityLoginBinding
import com.example.smartechnavigationdrawer.navigation.PrincipalNavigation
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{view ->

            val email = binding.EditEmail.text.toString()
            val senha = binding.EditSenha.text.toString()

            var comparacao = email
            var retorno = "@"
            var listar = comparacao
                .split(retorno)
                .takeLast(1)

            var adm = "[smartech.corp]"



            if(email.isEmpty() || senha.isEmpty()){
                val snackbar = Snackbar.make(view, "Preencha todos os campos!", Snackbar.LENGTH_LONG)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.setTextColor(Color.WHITE)
                snackbar.show()
            }else{
                if(listar.toString() == adm){

                auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener{autenticacao ->
                    if(autenticacao.isSuccessful){
                        Toast.makeText(this, "Logado como ADM!", Toast.LENGTH_LONG).show()
                        val verificar = Intent(this, PrincipalNavigation::class.java)
                        verificar.putExtra("coitado", "coitado")
                        startActivity(verificar)
                        //navegarTela()

                        }
                    }
                }
                    else{
                    auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener{autenticacao ->
                        if(autenticacao.isSuccessful){
                            val verificar = Intent(this, PrincipalNavigation::class.java)
                            verificar.putExtra("adm", "adm")
                            startActivity(verificar)
                            //navegarTela()


                        }
                }.addOnFailureListener{exception ->
                    val menssageErro = when(exception){
                        is FirebaseAuthWeakPasswordException -> "Senha incorreta!"
                        is FirebaseAuthInvalidCredentialsException -> "Digite um email valido!"
                        is FirebaseNetworkException -> "Sem conexão com a internet!"
                        else -> "Erro ao cadastrar o usuario!"
                    }
                    val snackbar = Snackbar.make(view, menssageErro, Snackbar.LENGTH_LONG)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.setTextColor(Color.WHITE)
                    snackbar.show()
                        }
                    }
                }
            }


        binding.txtCadastro.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,Cadastro::class.java))
        })


    }
    private fun navegarTela(){
        startActivity(Intent(this,PrincipalNavigation::class.java))
        finish()
    }

    override fun onStart() {
        super.onStart()
        val usuarioAtual = FirebaseAuth.getInstance().currentUser
        if(usuarioAtual != null){
            navegarTela()
        }
    }

}