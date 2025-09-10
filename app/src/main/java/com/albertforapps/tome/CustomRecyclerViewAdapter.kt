package com.albertforapps.tome

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CustomRecyclerViewAdapter(private val list: MutableList<ToDo>): RecyclerView.Adapter<ViewHolder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cust_recyclerview_notes, parent, false)

        context=parent.context
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        val db = Room.databaseBuilder(context, AppDatabase::class.java, "todos").build()
        val tododao=db.todoDao()



        holder.txtvTitle.text = item.title
        holder.txtvShortDesc.text = item.description.substring(0,item.description.length/2) + " . . ."

        holder.buttonDelete.setOnClickListener {

            GlobalScope.launch {


                tododao.delete(item.id)
            }

            list.removeAt(position)
            notifyItemRemoved(position)


        }


        holder.buttonEdit.setOnClickListener {


            GlobalScope.launch {
                val vv: Intent = Intent(context, noting_screen::class.java)
                vv.putExtra("title", item.title)
                vv.putExtra("description", item.description)
                vv.putExtra("id", item.id)
                context.startActivity(vv)

            }

        }
    }



}