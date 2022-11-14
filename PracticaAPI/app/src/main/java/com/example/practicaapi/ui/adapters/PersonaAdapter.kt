package com.example.practicaapi.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaapi.R
import com.example.practicaapi.models.Personas
import com.example.practicaapi.models.Post

class PersonaAdapter(
    val data: ArrayList<Personas>,
    val listener: PersonaListener
) : RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder>() {
    class PersonaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblId = itemView.findViewById<TextView>(R.id.lblId)
        val lblNombre = itemView.findViewById<TextView>(R.id.lblNombre)
        val lblApellido = itemView.findViewById<TextView>(R.id.lblApellido)
        val lblEdad = itemView.findViewById<TextView>(R.id.lblEdad)
        val lblCiudad = itemView.findViewById<TextView>(R.id.lblCiudad)
        val lblFechaNacimiento = itemView.findViewById<TextView>(R.id.lblFecha)
        val btnEditar = itemView.findViewById<TextView>(R.id.btnEdit)
        val btnEliminar = itemView.findViewById<TextView>(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.post_item_layout, parent, false)
        return PersonaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val persona = data[position]
        holder.lblNombre.text = persona.nombres
        holder.lblApellido.text = persona.apellidos
        holder.lblEdad.text = persona.edad.toString()
        holder.lblCiudad.text = persona.ciudad
        holder.lblFechaNacimiento.text = persona.fechaNacimiento
        holder.btnEditar.setOnClickListener {
            listener.onItemEdit(persona)
        }
        holder.btnEliminar.setOnClickListener {
            listener.onItemDelete(persona)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface PersonaListener {
        fun onItemEdit(persona: Personas)
        fun onItemDelete(persona: Personas)
    }
}
