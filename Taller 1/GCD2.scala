object GCDConcurrent {

  // Función auxiliar para invertir una lista sin usar reverse
  def reverseList(lst: List[Int], result: List[Int] = Nil): List[Int] = {
    if (lst.isEmpty) result
    else reverseList(lst.tail, lst.head :: result)
  }

  // Función que encuentra los factores primos de un número n
  def primeFactors(n: Int, factor: Int = 2): List[Int] = {
    if (n == 1) Nil //Ya no agrega mas parámetros a la lista
    else if (n % factor == 0) factor :: primeFactors(n / factor, factor)
    else primeFactors(n, factor + 1)
  }

  // Función que combina las listas de primos de dos números para obtener una lista de primos únicos
  def combinePrimes(primes1: List[Int], primes2: List[Int]): List[Int] = {
  
  // Función auxiliar recursiva que agrega un primo a la lista `primes` solo si no está ya presente.
  def addPrime(prime: Int, primes: List[Int]): List[Int] = {
    if (primes.isEmpty) List(prime)
    else if (prime == primes.head) primes
    else primes.head :: addPrime(prime, primes.tail)
  }

  // Función recursiva que combina las dos listas de primos `primes1` y `primes2`.
  def helper(list1: List[Int], list2: List[Int], result: List[Int]): List[Int] = {
    if (list1.isEmpty && list2.isEmpty) result
    else if (list1.isEmpty) helper(list1, list2.tail, addPrime(list2.head, result))
    // Si `list1` está vacía, agrega el primer elemento de `list2` al resultado si no está ya presente, 
    // y continúa con la cola de `list2`.
    else if (list2.isEmpty) helper(list1.tail, list2, addPrime(list1.head, result))
    // Si `list2` está vacía, agrega el primer elemento de `list1` al resultado si no está ya presente,
    // y continúa con la cola de `list1`.
    else {
      val newResult = addPrime(list1.head, result)
      // Si ambas listas tienen elementos, comienza agregando el primer elemento de `list1` al resultado.
      helper(list1.tail, list2, addPrime(list2.head, newResult))
      // Luego, agrega el primer elemento de `list2` al nuevo resultado y continúa de manera recursiva.
    }
  }
  helper(primes1, primes2, Nil) // Llama a la función `helper` con las dos listas de primos y una lista vacía como resultado inicial.
}

  // Función que calcula los exponentes de los factores primos para un número n
  def calculateExponents(n: Int, primes: List[Int]): List[Int] = {

    // Función auxiliar recursiva que cuenta las veces que un primo aparece en la lista de factores primos
    def countFactor(factor: Int, factors: List[Int], count: Int = 0): Int = {
      if (factors.isEmpty) count
      else if (factors.head == factor) countFactor(factor, factors.tail, count + 1)
      else countFactor(factor, factors.tail, count)
    }

    // Función auxiliar recursiva que recorre la lista de primos y construye la lista de exponentes
    def helper(primes: List[Int], factors: List[Int], result: List[Int]): List[Int] = {
      if (primes.isEmpty) result
      else {
        val count = countFactor(primes.head, factors) // Conteo de la aparición del primo actual
        helper(primes.tail, factors, count :: result) // Llamada recursiva con el resto de la lista y resultado acumulado
      }
    }

    // La lista `result` se construye de atrás hacia adelante, pero no usaremos reverse,
    // por lo que en su lugar haremos que `helper` lo haga en el orden correcto.
    def reverseList(lst: List[Int], result: List[Int] = Nil): List[Int] = {
      if (lst.isEmpty) result
      else reverseList(lst.tail, lst.head :: result)
    }

    reverseList(helper(primes, primeFactors(n), Nil))
  }

  // Función que calcula el máximo común divisor (mcd) de dos números
  def gcd(n: Int, m: Int): Int = {
    if (m == 0) n else gcd(m, n % m)
  }

  def main(args: Array[String]): Unit = {
    val (n, m) = (120, 500)

    val primes1 = primeFactors(n) // Obtiene factores primos de n
    val primes2 = primeFactors(m) // Obtiene factores primos de m
    val primes = combinePrimes(primes1, primes2) // Combina las listas de factores primos de n y m

    val Ln = calculateExponents(n, primes) // Calcula los exponentes de n según los primos combinados
    val Lm = calculateExponents(m, primes) // Calcula los exponentes de m según los primos combinados
    val mcd = gcd(n,m) //Calcula el gcd

    println(s"Ln: $Ln")
    println(s"Lm: $Lm")
    println(s"Primos comunes: $primes")
    println(s"mcd: $mcd")
  }
}
