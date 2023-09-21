package com.example.smartechnavigationdrawer.navigation.ui.cursos.containervideos.config

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.smartechnavigationdrawer.R

class CommentExample : RecyclerView.Adapter<CommentExample.VerticalHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VerticalHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.container_coment, parent, false)
    )

    override fun onBindViewHolder(holder: VerticalHolder, position: Int) {
    }

    override fun getItemCount(): Int = 10

    class VerticalHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    }


}