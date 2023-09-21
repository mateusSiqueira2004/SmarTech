package com.example.smartechnavigationdrawer.navigation.ui.Administrador

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smartechnavigationdrawer.R
import com.example.smartechnavigationdrawer.databinding.DialogFuncionarioBinding
import com.example.smartechnavigationdrawer.databinding.FragmentCadastrarADMBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.random.Random

class Cadastrar_ADM : Fragment(R.layout.fragment_cadastrar__a_d_m) {


    private lateinit var binding: FragmentCadastrarADMBinding
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private lateinit var dialog: android.app.AlertDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCadastrarADMBinding.inflate(inflater, container, false)
        return binding.root


    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val funcao: Spinner = binding.SpinnerFuncao
        var emailNovo:String
        var cargo:String

        ArrayAdapter.createFromResource(
            requireContext(), R.array.SpinnerFunction, android.R.layout.simple_spinner_item
        ).also{adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            funcao.adapter = adapter
             cargo = funcao.textAlignment.toString()
        }

        funcao.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                cargo = parent!!.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }


        binding.btnCadastrarFun.setOnClickListener {

            var senha = binding.editSenhafun.text.toString()
            var nome = binding.editnomeCompleto.text.toString()
            val nomeS = nome.replace("\\s".toRegex(), " ".trim())
            val n = Random.nextInt(10).toString()
            emailNovo = "$nomeS$n@smartech.corp"
                if (senha.isEmpty() || funcao.isEmpty() || nome.isEmpty()) {
                    val snackbar =
                        Snackbar.make(view, "Preencha todos os campos!", Snackbar.LENGTH_LONG)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.setTextColor(Color.WHITE)
                    snackbar.show()
                } else {
                    auth.createUserWithEmailAndPassword(emailNovo, senha)
                        .addOnCompleteListener { cadastrar ->
                            if (cadastrar.isSuccessful) {

                                val build = android.app.AlertDialog.Builder(context, R.style.ThemaCustomDialog)

                                val dialogBinding: DialogFuncionarioBinding = DialogFuncionarioBinding.inflate(LayoutInflater.from(context))

                                dialogBinding.EmailFuncionario.text = emailNovo


                                val userid = FirebaseAuth.getInstance().currentUser?.uid
                                val usuariosMap = hashMapOf(
                                    "nome" to binding.editnomeCompleto.text.toString(),
                                    "email" to emailNovo,
                                    "telefone" to binding.Edittele.text.toString(),
                                    "funcao" to  cargo
                                )
                                db.collection("Funcionario").document(userid.toString()).set(usuariosMap).addOnCompleteListener {
                                    Log.d("db", "sucesso ao salvar os dados do usuario")
                                }

                                dialogBinding.ok.setOnClickListener{dialog.dismiss()
                                    findNavController().navigate(R.id.action_cadastrar_adm_to_nav_geruser)}

                                build.setView(dialogBinding.root)
                                dialog = build.create()
                                dialog.show()


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
                }
        }
    }
}
