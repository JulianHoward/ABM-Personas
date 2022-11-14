package com.example.practicaapi.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.practicaapi.R
import com.example.practicaapi.models.Personas
import com.example.practicaapi.repositories.PersonaRepository

class FormPersona : AppCompatActivity(), PersonaRepository.CreatePersonaListener,
    PersonaRepository.EditPersonaListener {
    private lateinit var btnCrearPersona: Button
    private lateinit var nombre: EditText
    private lateinit var apellido: EditText
    private lateinit var edad: EditText
    private lateinit var ciudad: EditText
    private lateinit var fechaNacimiento: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_persona)
        btnCrearPersona = findViewById(R.id.btnCreate)
        nombre = findViewById(R.id.txtNombres2)
        apellido = findViewById(R.id.txtApellidos2)
        edad = findViewById(R.id.txtEdad2)
        ciudad = findViewById(R.id.txtCiudad2)
        fechaNacimiento = findViewById(R.id.txtFecha2)
        setupEventListeners()
    }

    private fun setupEventListeners() {
        val personaId = intent.getSerializableExtra("persona") as Personas?
        if (personaId != null) {
            nombre.setText(personaId.nombres)
            apellido.setText(personaId.apellidos)
            edad.setText(personaId.edad.toString())
            ciudad.setText(personaId.ciudad)
            fechaNacimiento.setText(personaId.fechaNacimiento)
        }
        btnCrearPersona.setOnClickListener {
            val persona = Personas(
                nombres = nombre.text.toString(),
                apellidos = apellido.text.toString(),
                edad = edad.text.toString().toInt(),
                ciudad = ciudad.text.toString(),
                fechaNacimiento = fechaNacimiento.text.toString()
            )
            if (persona.nombres != "" && persona.apellidos != "" && persona.edad != 0 && persona.ciudad != "" && persona.fechaNacimiento != "") {
                PersonaRepository.createPersona(persona, this)
            } else {
                Toast.makeText(this@FormPersona, "Debe llenar todos los campos", Toast.LENGTH_SHORT)
                    .show()
            }
            if (personaId != null) {
                PersonaRepository.editPersona(personaId.id, persona, this)
            }
        }
    }

    override fun onCreatePersonaSuccess(personas: Personas?) {
        finish()
    }

    override fun onCreatePersonaFailure(t: Throwable) {
        Toast.makeText(this@FormPersona, t.message, Toast.LENGTH_SHORT).show()
    }

    override fun onEditPersonaSuccess(personas: Personas?) {
        finish()
    }

    override fun onEditPersonaFailure(t: Throwable) {
        Toast.makeText(this@FormPersona, t.message, Toast.LENGTH_SHORT).show()
    }
}