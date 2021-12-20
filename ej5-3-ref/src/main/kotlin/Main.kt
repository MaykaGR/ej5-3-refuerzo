import kotlin.system.exitProcess

data class Contacto(val nombre: String, val tlf: String){
    companion object{
        fun crearContacto(nombre: String, tlf: String): Contacto{
            var nuevoContacto = Contacto(nombre,tlf)
            return nuevoContacto
        }
    }
}
data class Agenda(val datos: MutableMap<String, String>){
    fun add(contacto: Contacto): MutableMap <String, String>{
        datos.set(contacto.nombre,contacto.tlf)
        return datos
    }

        fun buscar(comparador: String): Boolean{
            if(comparador in datos.keys){return true}
            else if(comparador in datos.values){return true}
            else return false
    }
}

fun main() {
    var agenda = Agenda(mutableMapOf())
    val contacto = Contacto("LAURA", "857438287")
    agenda.add(contacto)
    println("Introduce nombre o tlf a buscar: ")
    var comparador = readLine()?.toUpperCase()?: ""
    if (agenda.buscar(comparador)==true){
       if(comparador in agenda.datos.keys){
            println(agenda.datos[comparador])
        } else {println(agenda.datos.filterValues{ it.contains(comparador)})}
    }
    else{
        println("El contacto no está en agenga, vamos a incluirlo")
        println("Introduce nombre: ")
        var nombre = readLine()?.toUpperCase()?: ""
        println("Introduce tlf: ")
        var tlf = readLine()?: ""
        agenda.add(Contacto.crearContacto(nombre,tlf))
    }
    println(agenda)
    println("Introduce lo que quieres hacer ahora (adios, listado, filtra): ")
    when(readLine()?: ""){
        "adios" -> System.exit(0)
        "listado" -> println(agenda)
        "filtra" -> println("¿Qué deseas buscar?")
        else -> println("Comando no reconocido")
    }
    comparador = readLine()?.toUpperCase()?: ""
    if(comparador in agenda.datos.keys){
            println(agenda.datos.filterKeys{ it.contains(comparador)})
        }
    else if(comparador in agenda.datos.values){
        println(agenda.datos.filterValues{ it.contains(comparador)})
    }
    else println("Ningún contacto")
}