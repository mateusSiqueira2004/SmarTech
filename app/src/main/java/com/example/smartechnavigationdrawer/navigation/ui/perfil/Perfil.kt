package com.example.smartechnavigationdrawer.navigation.ui.perfil

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smartechnavigationdrawer.Login
import com.example.smartechnavigationdrawer.R
import com.example.smartechnavigationdrawer.databinding.DialogExcluirBinding
import com.example.smartechnavigationdrawer.databinding.FragmentPerfilBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

class Perfil : Fragment() {
    private lateinit var binding: FragmentPerfilBinding
    private val db = FirebaseFirestore.getInstance()
    var storage:FirebaseStorage? = null
    var storageRef = FirebaseStorage.getInstance().getReference()
    val userid = FirebaseAuth.getInstance().currentUser?.uid
    val auth = FirebaseAuth.getInstance()
    private lateinit var dialog: AlertDialog
    var escolha = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentPerfilBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        puxarDados()

        binding.btneditarPerfil.setOnClickListener{
            findNavController().navigate(R.id.action_nav_perfil_to_nav_editar_perfil)
        }
        binding.deletarPerfil.setOnClickListener{
            deletarConta()
        }

        storage = Firebase.storage

         binding.imageButtonUser.setOnClickListener{
             val builder =AlertDialog.Builder(context)
             builder.setTitle("Escolha a origem da foto!")
             builder.setPositiveButton("Camera"){dialog, which ->
                 ChamarCamera()}
             builder.setNegativeButton("Galeria"){dialog, which ->
                 ChamarGaleria()
             }
             builder.setNeutralButton("Cancelar"){_,_ ->
                 Toast.makeText(context,"Você cancelou a ação.",Toast.LENGTH_SHORT).show()
             }
             val dialog: AlertDialog = builder.create()
             dialog.show()
         }

         storageRef.child("gs://smartech-bb31e.appspot.com"+userid+".jpeg").downloadUrl.addOnSuccessListener {

         }

    }

    private fun puxarDados(){
        val userid = FirebaseAuth.getInstance().currentUser?.uid
        db.collection("Usuários").document(userid.toString()).addSnapshotListener { documento, erro ->
            if(documento != null){
                binding.nomeUser.text = documento.getString("nome")
                binding.emailUser.text = documento.getString("email")
            }

        }
    }
    private fun deletarConta(){
        val build = AlertDialog.Builder(context, R.style.ThemaCustomDialog)

        val dialogBinding: DialogExcluirBinding = DialogExcluirBinding.inflate(LayoutInflater.from(context))

        dialogBinding.btnCancelar.setOnClickListener{dialog.dismiss()}
        dialogBinding.btnExcluir.setOnClickListener{
           val email =  dialogBinding.editEmailExcluir.text.toString()
            val senha = dialogBinding.editSenhaExcluir.text.toString()

            auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener { autenticacao ->
                if(autenticacao.isSuccessful){
                    db.collection("Usuários").document(userid.toString()).delete().addOnSuccessListener {
                        Toast.makeText(context, "Dados excluido com sucesso!", Toast.LENGTH_SHORT).show()
                    }
                    auth.currentUser!!.delete()
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(activity, Login::class.java))
                    activity?.finish()

                }
            }.addOnFailureListener {exception ->
                    val menssageErro = when(exception){
                        is FirebaseAuthWeakPasswordException -> "Senha incorreta!"
                        is FirebaseAuthInvalidCredentialsException -> "Digite um email valido!"
                        is FirebaseNetworkException -> "Sem conexão com a internet!"
                        else -> "Erro ao excluir o usuario!"
                    }
                    Toast.makeText(context, menssageErro, Toast.LENGTH_LONG).show()
                }

            }

        build.setView(dialogBinding.root)
        dialog = build.create()
        dialog.show()
    }
        private fun uploadImg(){

            val bitmap = (binding.imageButtonUser.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50,baos)

            val data = baos.toByteArray()

            val reference = storage!!.reference.child("imagensUser").child(userid.toString())


            var uploadTask = reference.putBytes(data)
            uploadTask.addOnSuccessListener {
               val toast = Toast.makeText(context, "Sucesso ao realizar o upload",Toast.LENGTH_LONG)
                toast.show()
            }.addOnFailureListener{ taskSnapshot ->
                val toast = Toast.makeText(context, "Falha ao realizar o upload",Toast.LENGTH_LONG)
                toast.show()
            }


        }
        val REQUEST_IMAGE_CAPTURE = 1
        private fun ChamarCamera(){
            escolha = 1
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }catch (e: ActivityNotFoundException){
                val toast = Toast.makeText(context, "Falha ao abrir a camera",Toast.LENGTH_LONG)
                toast.show()
            }

        }
        private fun ChamarGaleria(){
            escolha = 2
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, 1)
        }

}
