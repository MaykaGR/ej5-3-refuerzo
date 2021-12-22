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
fun nombre(palabra: String): Boolean{
    if (palabra.first().isLetter()){return true}
    else return false
}
fun tlf(palabra: String): Boolean{
    if(palabra.first().isDigit()){return true}
    else return false
}
//Función que se realiza a cabo con el comando especial filtrar
fun filtrar(agenda: Agenda){
        var name = ""
        var phone = ""
    //Tras introducir filtrar, la función pide el texto a filtrar
        println("Introduce nombre o tlf a buscar")
    //Se pasa lo que se introduzca a mayúsculas para que la búsqueda sea independiente de cómo se introduzca el nombre
        var comparador = readLine()?.toUpperCase()?: ""
    //Se busca si el contacto está en la agenda
        if (agenda.buscar(comparador) == true) {
            // Si lo está, miramos si lo introducido es el nombre o el número para mostrar el resto de datos
            if (comparador in agenda.datos.keys) {
                println(agenda.datos[comparador])
            } else {
                println(agenda.datos.filterValues { it.contains(comparador) })
            }
        }
        // Sino lo está, introducimos un nuevo contacto
        else {
            println("El contacto no está en agenda, vamos a incluirlo")
            if (tlf(comparador) == true) {
                println("Introduce nombre: ")
                name = readLine()?.toUpperCase() ?: ""
                phone = comparador
            } else if (nombre(comparador) == true) {
                println("Introduce tlf: ")
                phone = readLine() ?: ""
                name = comparador
            } else {
                println("error")
            }
            agenda.add(Contacto.crearContacto(name, phone))
        }
    }
//Función para el comando especial listado, que imprime el listado de la agenda
//La acción de mostrar los contactos ordenados por orden alfabético también la incluyo en esta función
//Así la muestro directamente ordenada
fun imprimir(agenda: Agenda){
    //for(i in 0..agenda.datos.size){}
    var agendaOrdenada = agenda.datos.toSortedMap()
    println(agendaOrdenada)
}

fun main() {
    // Instancia de clase agenda
    var agenda = Agenda(mutableMapOf())
    // Instancia de clase contacto
    val contacto = Contacto("LAURA", "857438287")
    // Añadir contacto a agenda
    agenda.add(contacto)
    var accion: String = ""
    //Bucle para que se lleve a cabo usándolo según los comandos especiales
    do {
        println("Introduce accion (filtrar, listado o adios): ")
        //Se pasa lo que se introduzca a mayúsculas para que la búsqueda sea independiente de cómo se introduzca el nombre
        accion = readLine()?.toUpperCase() ?: ""
        when(accion){
            "FILTRAR" -> filtrar(agenda)
            "LISTADO" -> imprimir(agenda)
            "ADIOS" -> System.exit(0)
            else -> println("Comando desconocido")
        }

    }
        //Se repite hasta que se teclee adios
        while(accion !== "ADIOS")
        System.exit(0)
}
