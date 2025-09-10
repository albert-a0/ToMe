package com.albertforapps.tome

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class noting_screen : AppCompatActivity() {

    lateinit var ii: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_noting_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        ii = getIntent()
        if(ii.hasExtra("title") && ii.hasExtra("description") && ii.hasExtra("id")){
            findViewById<EditText>(R.id.txtinp_title).setText(ii.getStringExtra("title"))
            findViewById<EditText>(R.id.txtinp_notes).setText(ii.getStringExtra("description"))
            findViewById<Button>(R.id.btn_save).setText("UPDATE")
        }else{
            findViewById<Button>(R.id.btn_save).setText("SAVE")
        }


    }

    fun saveitClick(v: View){
        val title = findViewById<EditText>(R.id.txtinp_title).text.toString()
        val notes = findViewById<EditText>(R.id.txtinp_notes).text.toString()

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "todos").build()
        val tododao=db.todoDao()

        try{
            if(ii.hasExtra("id")){
                GlobalScope.launch {
                    tododao.update(ii.getExtras()!!.getInt("id"), title, notes)
                    val i: Intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(i)
                }
            }else{
                GlobalScope.launch {
                    tododao.insert(title, notes)
                    val i: Intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(i)
                }
            }
        }catch (e: Exception){

        }



    }
}