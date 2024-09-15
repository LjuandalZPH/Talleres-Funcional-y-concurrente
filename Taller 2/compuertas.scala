//Creamos un alias de tipo Chip
type Chip = List[Int] => List[Int]

//1.1.1
def crearChipUnario(funcion:Int => Int): Chip = {
    //Implementamos una función para recorrer listas
    def aplicarFuncion(bits:List[Int]):List[Int] = { 
        if (bits.isEmpty)Nil
        else funcion(bits.head) :: aplicarFuncion(bits.tail)
    }
    aplicarFuncion //Devolvemos la función
}
val chip_not = crearChipUnario(x => 1-x)
val salida = chip_not(List(1))

// fin 1.1.1