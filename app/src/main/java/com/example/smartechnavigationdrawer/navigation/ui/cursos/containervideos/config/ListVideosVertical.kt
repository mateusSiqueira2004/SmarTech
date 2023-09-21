package com.example.smartechnavigationdrawer.navigation.ui.cursos.containervideos.config

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.smartechnavigationdrawer.R
import com.example.smartechnavigationdrawer.navigation.ui.cursos.containervideos.ListaDeCursosFragment

class ListVideosVertical(var onClick: ListaDeCursosFragment,var videosList: ArrayList<data>) : RecyclerView.Adapter<ListVideosVertical.VerticalHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_listacursos, parent, false)
        return VerticalHolder(itemView)
    }

    interface ClickVideo{
        fun clickVideo(ListaVideos: data)
    }


    override fun onBindViewHolder(holder: VerticalHolder, position: Int) {
        //val currentitem = videosList[position]

        //holder.video_tittle.text = currentitem.title
        //holder.videos_subtittle.text = currentitem.subTittle

        holder.constraintLayout.setOnClickListener{
           onClick.clickVideo(videosList)

        }
    }

    override fun getItemCount(): Int = 10/*{
        return videosList.size
    }*/

    class VerticalHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        //var video_tittle: TextView = itemView.findViewById(R.id.video_tittle)
        //var videos_subtittle:TextView = itemView.findViewById(R.id.video_subtittle)
        //var videos_tumb = itemView.findViewById<ImageView>(R.id.video_thumbnail)

        /*fun bind(videosList: data) {
            with(itemView){
                video_tittle.text = videosList.title
                videos_subtittle.text = videosList.subTittle

            }
        }*/
        val constraintLayout = itemView.findViewById<ConstraintLayout>(R.id.videos_list)
    }
}

