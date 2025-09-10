package com.albertforapps.tome

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.PI

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val recy: RecyclerView = findViewById<RecyclerView>(R.id.recy_note)
        recy.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        val d = ArrayList<ToDo>()

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "todos").build()
        val tododao=db.todoDao()

        GlobalScope.launch {

            tododao.getAll().forEach {
                d.add(ToDo(it.id, it.title, it.description))
            }

        }
        recy.adapter = CustomRecyclerViewAdapter(d)

    }

    override fun onResume() {
        super.onResume()


    }

    fun createNoteClick(v: View){
        val createNoteScreen:Intent = Intent(this, noting_screen::class.java)
        startActivity(createNoteScreen)
    }
}