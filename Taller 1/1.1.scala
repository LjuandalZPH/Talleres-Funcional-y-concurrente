//Función que obtiene los factores primos de un número m
def descomponer(x:Int, primo:Int = 2, listPrime : List[Int] = Nil):List[Int] =
    {
    if (x%primo==0 && x!=1) 
        descomponer(x/primo,primo, primo::listPrime)
    else if (x!=1) 
        descomponer(x,primo+1,listPrime)
    else 
        listPrime
    }
//Función para sacar una lista que contiene los primos comúnes de dos números m y n
def compararPrimosComunes(lista1 : List[Int],lista2 : List[Int], listaFinal :List[Int]=Nil): List[Int] =
    {
    if (lista1.isEmpty) listaFinal
    else if (lista2.contains(lista1.head)) compararPrimosComunes(lista1.tail,lista2, lista1.head::listaFinal )
    else compararPrimosComunes(lista1.tail,lista2,listaFinal)
    }

//Función que multiplica los elementos de la listaFinal para hallar el MCD
def encontrarMcd(primosComunes:List[Int], mcd:Int = 1):Int =
    {
    if (primosComunes.isEmpty) mcd
    else encontrarMcd(primosComunes.tail,mcd*primosComunes.head)
    }
 
//Función pedida por el taller
//def modTFA(ln: List[Int], lm : List[Int], primos : List[Int]):Int =

//def mcd(m:Int,n:int) =

   
//encontrarMcd(compararPrimosComunes(descomponer(x),descomponer(y)))

    
  

