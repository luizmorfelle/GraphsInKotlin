class GrafoMatriz(override val direcionado: Boolean = true, override val ponderado: Boolean = true) : Grafo() {
    override fun imprimeGrafo() {
        print("  ")
        vertices.forEach { (label, _) -> print("$label ") }
        println()
        for (verticeX in vertices) {
            print("${verticeX.key} ")
            for (verticeY in vertices) {
                if (verticeX.value.vizinhos.containsKey(verticeY.value)) print("1 ")
                else print("0 ")
            }
            println()
        }
    }
}