package com.example.smartechnavigationdrawer.navigation.ui.chat

import android.content.ContentValues.TAG
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smartechnavigationdrawer.R
import com.example.smartechnavigationdrawer.databinding.FragmentChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Comment


fun Int.toDp() : Int = (this / Resources.getSystem().displayMetrics.density).toInt()
fun Int.toPx() : Int = (this * Resources.getSystem().displayMetrics.density).toInt()



class ChatFragment : Fragment() {

    private lateinit var db: DatabaseReference
    val dbf = Firebase.firestore
    val userid = FirebaseAuth.getInstance().currentUser?.uid
    var iniciado = 0
    var iniciadod = 0
    var jafoi=1
    lateinit var nome :String
    lateinit var txt :String

// ...




    private lateinit var binding: FragmentChatBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = Firebase.database.reference

        Enviar()
    }
    fun bot1(){
        if(iniciado ==1){
            val taView = TextView(context)
            taView.text = "Você Selecionou Duvida.\n"+
                    "1 - Duvidas com Hardware\n" +
                    "2 - Duvidas com Programação\n" +
                    "3 - Duvidas com Redes"
            taView.setPadding(20, 5, 20, 5)
            binding.layChat.addView(taView)
        }else if(iniciado ==2){
            val taView = TextView(context)
            taView.text = "Você Selecionou Suporte. \n"+
                    "1 - Para duvidas com a plataforma. \n" +
                    "2 - Para problemas com pagamentos. \n" +
                    "3 - Para reportar algum problema na plataforma."
            taView.setPadding(20, 5, 20, 5)
            binding.layChat.addView(taView)

        }

    }
    fun bot2(){
       if(jafoi.equals(1)) {
            var int: Int
            if (txt == "1" || txt == "2" || txt == "3") {
                int = Integer.parseInt(txt)
            }
            if (txt == "1") {
                val teView = TextView(context)
                teView.text =
                    "Você selecionou Duvidas com Hardware.\nEm alguns instantes um especialista ira\nte ajudar. "
                teView.setPadding(20, 5, 20, 5)
                binding.layChat.addView(teView)
            } else if (txt == "2") {
                val teView = TextView(context)
                teView.text =
                    "Você selecionou Duvidas em Programação.\nEm alguns instantes um especialista ira\nte ajudar. "
                teView.setPadding(20, 5, 20, 5)
                binding.layChat.addView(teView)
            } else if (txt == "3") {
                val teView = TextView(context)
                teView.text =
                    "Você selecionou Duvidas em Redes.\nEm alguns instantes um especialista ira\nte ajudar. "
                teView.setPadding(20, 5, 20, 5)
                binding.layChat.addView(teView)
            }
            jafoi =0}


    }
    fun bot3(){
      if(jafoi.equals(1)) {
            var int: Int
            if (txt == "1" || txt == "2" || txt == "3") {
                int = Integer.parseInt(txt)
            }
            if (txt == "1") {
                val teView = TextView(context)
                teView.text =
                    "Você selecionou Duvidas com a plataforma.\nEm alguns instantes um sera atendido"
                teView.setPadding(20, 5, 20, 5)
                binding.layChat.addView(teView)
            } else if (txt == "2") {
                val teView = TextView(context)
                teView.text =
                    "Você selecionou Problemas com pagamentos.\nEm alguns instantes um sera atendido"
                teView.setPadding(20, 5, 20, 5)
                binding.layChat.addView(teView)
            } else if (txt == "3") {
                val teView = TextView(context)
                teView.text =
                    "Você selecionou Reportar problema na plataforma.\nEnvie sua mensagem."
                teView.setPadding(20, 5, 20, 5)
                binding.layChat.addView(teView)
            }
           jafoi =0}


    }


    fun Enviar() {
        val docRef = dbf.collection("Usuários").document(userid.toString())
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    nome = document.get("nome").toString()
                    Log.d(TAG, nome)
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }



        binding.btnDuvida.setOnClickListener{
            val tView = TextView(context)
            tView.text = "Você Selecionou DUVIDA. Siga as Intruçoes para receber o atendimento!"
            tView.setPadding(20, 5, 20, 5)
            tView.id = View.generateViewId()
            binding.layBtn.addView(tView)
            binding.btnDuvida.visibility = View.GONE
            binding.btnSuporte.visibility = View.GONE
            iniciado = 1
            bot1()
            Log.i("iniciado", tView.id.toString())
        }
        binding.btnSuporte.setOnClickListener{
            val tView = TextView(context)
            tView.text = "Você Selecionou SUPORTE. Siga as Intruçoes para receber o atendimento!"
            tView.setPadding(20, 5, 20, 5)
            tView.id = View.generateViewId()
            binding.layBtn.addView(tView)
            binding.btnDuvida.visibility = View.GONE
            binding.btnSuporte.visibility = View.GONE
            iniciado = 2
            bot1()
        }


        binding.btnEnviar.setOnClickListener {
            var edtEnviar = binding.edtMsg.text.toString()
            if(!edtEnviar.isEmpty()) {
                if (!edtEnviar.isEmpty()) {

                    val eView = TextView(context)
                    eView.setPadding(5, 0, 20, 10)
                    eView.id = View.generateViewId()
                    eView.gravity = Gravity.RIGHT
                    eView.text = edtEnviar
                    binding.layChat.addView(eView)
                    binding.edtMsg.text.clear()
                    Log.e("user", eView.id.toString())

                    var edtId = eView.id.toString()
                    var userId = userid.toString()
                    Log.e("user", userId)
                    txt = edtEnviar


                    db.child("Chats").child(userId).child(edtId).child("idMsg").setValue(edtId)
                    db.child("Chats").child(userId).child(edtId).child("msg").setValue(edtEnviar)
                    db.child("Chats").child(userId).child(edtId).child("user").setValue(userId)
                    if (iniciado == 0 && iniciadod == 0) {
                        val eView = TextView(context)
                        eView.setPadding(20, 5, 5, 5)
                        eView.id = View.generateViewId()
                        eView.text = "Escolha DUVIDA ou SUPORTE para iniciar o Atendimento"
                        binding.layChat.addView(eView)
                        iniciadod = 1
                    }
                } else {
                    Toast.makeText(activity, "Campo Vazio", Toast.LENGTH_LONG).show()
                }
                if (iniciado == 1) {
                    bot2()
                } else if (iniciado == 2) {
                    bot3()
                }

            }else{Toast.makeText(activity, "Campo Vazio", Toast.LENGTH_LONG).show()}
/*

            b2.text = getString(R.string.b1)
            binding.layChat.addView(b1)
            val b2 = Button(context)
            dView.text = getString(R.string.b2)
            binding.layChat.addView(b2)

 */
        }



        binding.btnVoltar.setOnClickListener{
            findNavController().navigate(R.id.action_nav_chat_to_nav_home)

        }
    }

}




