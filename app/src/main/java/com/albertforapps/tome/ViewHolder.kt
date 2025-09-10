package com.albertforapps.tome

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txtvTitle: TextView = view.findViewById<TextView>(R.id.txtv_title)
    val txtvShortDesc: TextView = view.findViewById<TextView>(R.id.txtv_short_note)
    val buttonEdit: TextView = view.findViewById<TextView>(R.id.button2)
    val buttonDelete: TextView = view.findViewById<TextView>(R.id.button3)
}
