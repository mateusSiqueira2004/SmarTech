package com.example.smartechnavigationdrawer.navigation.ui.cursos.containervideos

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.smartechnavigationdrawer.R
import com.example.smartechnavigationdrawer.databinding.FragmentListaDeCursosBinding
import com.example.smartechnavigationdrawer.navigation.ui.cursos.containervideos.config.*
import com.google.firebase.database.*
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore


class ListaDeCursosFragment : Fragment(R.layout.fragment_lista_de_cursos) {
    private lateinit var binding: FragmentListaDeCursosBinding
    private var listVideosVertical: ListVideosVertical? = null

    private lateinit var videosList: ArrayList<data>
    private lateinit var dbref: DatabaseReference
    private lateinit var recyclerView: RecyclerView

    private var db = FirebaseFirestore.getInstance()
    private var notebookRef: CollectionReference = db.collection("notebook")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListaDeCursosBinding.inflate(inflater, container,false)
        return binding.root
    }


    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*recyclerView = view.findViewById(R.id.lista_cursos_vertical)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)*/

        listVideosVertical = ListVideosVertical(this, videosList = ArrayList())
        view.findViewById<RecyclerView>(R.id.lista_cursos_vertical).adapter = listVideosVertical

        /*videosList = arrayListOf<data>()

        getUserData()

    }

    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("notebook")

        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    Log.e("socooro", "MEU DEUUUUSS")
                    for (videoSnapshot in snapshot.children){
                        val note = videoSnapshot.getValue(data::class.java)
                        videosList.add(note!!)

                    }
                    recyclerView.adapter = ListVideosVertical(videosList)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, "Deu Ruim", Toast.LENGTH_LONG).show()
            }
        })*/
    }

        fun clickVideo(clickVideo: ArrayList<data>){
        startActivity(Intent(activity, PlayerActivity::class.java))
    }
}