package com.example.practicaapi.models

class Personas(
    val id: Int = 0,
    var nombres: String = "",
    var apellidos: String = "",
    var edad: Int = 0,
    var ciudad: String = "",
    var fechaNacimiento: String = ""
) : java.io.Serializable {

}
