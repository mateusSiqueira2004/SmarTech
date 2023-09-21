package com.example.smartechnavigationdrawer

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.smartechnavigationdrawer.databinding.ActivityCadastroBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore


class Cadastro : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCadastrar.setOnClickListener {view ->
            val email = binding.editEmail.text.toString()
            val senha = binding.editsenha.text.toString()
            val tele = binding.EdiTele.text.toString()
            val name = binding.editName.text.toString()
            val confsenha = binding.editConfSenha.text.toString()

            if(email.isEmpty() || senha.isEmpty() || name.isEmpty() || tele.isEmpty() || confsenha.isEmpty()) {
                val snackbar = Snackbar.make(view, "Preencha todos os campos!", Snackbar.LENGTH_LONG)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.setTextColor(Color.WHITE)
                snackbar.show()
            }else{
                val comparacao = email
                val retorno = "@"
                val listar = comparacao
                    .split(retorno)
                    .takeLast(1)

                val adm = "[smartech.corp]"


                if(listar.toString() == adm){
                    Toast.makeText(this, "Este email não pode ser cadastrado!", Toast.LENGTH_LONG).show()
                }else{
                    if (senha.equals(confsenha)) {
                        auth.createUserWithEmailAndPassword(email, senha)
                            .addOnCompleteListener { cadastro ->
                                if (cadastro.isSuccessful) {
                                    val snackbar = Snackbar.make(
                                        view, "sucesso ao cadastrar o usuario!",
                                        Snackbar.LENGTH_LONG
                                    )
                                    snackbar.setBackgroundTint(Color.GREEN)
                                    snackbar.setTextColor(Color.WHITE)
                                    snackbar.show()
                                    dados()
                                    this.finish()

                                }

                            }.addOnFailureListener { exception ->
                            val menssageErro = when (exception) {
                                is FirebaseAuthWeakPasswordException -> "Digite uma senha com no minimo 6 caracteres!"
                                is FirebaseAuthInvalidCredentialsException -> "Digite um email valido!"
                                is FirebaseAuthUserCollisionException -> "Esta conta já existe!"
                                is FirebaseNetworkException -> "Sem conexão com a internet!"
                                else -> "Erro ao cadastrar o usuario!"
                            }
                            val snackbar = Snackbar.make(view, menssageErro, Snackbar.LENGTH_LONG)
                            snackbar.setBackgroundTint(Color.RED)
                            snackbar.setTextColor(Color.WHITE)
                            snackbar.show()

                        }
                    } else {
                        val snackbar =
                            Snackbar.make(view, "As senhas não são iguais!", Snackbar.LENGTH_LONG)
                        snackbar.setBackgroundTint(Color.RED)
                        snackbar.setTextColor(Color.WHITE)
                        snackbar.show()
                    }
                }
            }
        }


    }
    private fun dados(){
        val userid = FirebaseAuth.getInstance().currentUser?.uid
        val usuariosMap = hashMapOf(
            "nome" to binding.editName.text.toString(),
            "email" to binding.editEmail.text.toString(),
            "telefone" to binding.EdiTele.text.toString()
        )
        db.collection("Usuários").document(userid.toString()).set(usuariosMap).addOnCompleteListener {
            Log.d("db", "sucesso ao salvar os dados do usuario")
        }
    }

}