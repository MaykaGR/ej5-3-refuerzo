import kotlin.system.exitProcess
// Clase para agrupar nombre con teléfono
data class Contacto(val nombre: String, val tlf: String){
    companion object{
        fun crearContacto(nombre: String, tlf: String): Contacto{
            var nuevoContacto = Contacto(nombre,tlf)
            return nuevoContacto
        }
    }
}
//Clase agenda que recoge los datos de contactos 
data class Agenda(val datos: MutableMap<String, String>){
    //Función que recibe un contacto y lo añade al conjunto de datos de la agenda
    fun add(contacto: Contacto): MutableMap <String, String>{
        datos.set(contacto.nombre,contacto.tlf)
        return datos
    }
    //Función que busca si un contacto está o no en la agenda, por nombre o por número de teléfono, útil si sólo queremos saber si está o no
        fun buscar(comparador: String): Boolean{
            if(comparador in datos.keys){return true}
            else if(comparador in datos.values){return true}
            else return false
    }
}

fun main() {
    // Instancia de clase agenda
    var agenda = Agenda(mutableMapOf())
    // Instancia de clase contacto
    val contacto = Contacto("LAURA", "857438287")
    // Añadir contacto a agenda
    agenda.add(contacto)
    println("Introduce nombre o tlf a buscar: ")
    //Se pasa lo que se introduzca a mayúsculas para que la búsqueda sea independiente de cómo se introduzca el nombre
    var comparador = readLine()?.toUpperCase()?: ""
    // Comprobamos si el contacto está en la agenda
    if (agenda.buscar(comparador)==true){
        // Si lo está, miramos si lo introducido es el nombre o el número para mostrar el resto de datos
       if(comparador in agenda.datos.keys){
            println(agenda.datos[comparador])
        } else {println(agenda.datos.filterValues{ it.contains(comparador)})}
    }
    // Sino lo está, introducimos un nuevo contacto
    else{
        println("El contacto no está en agenda, vamos a incluirlo")
        println("Introduce nombre: ")
        var nombre = readLine()?.toUpperCase()?: ""
        println("Introduce tlf: ")
        var tlf = readLine()?: ""
        agenda.add(Contacto.crearContacto(nombre,tlf))
    }
    println(agenda)
    // Comandos especiales
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
