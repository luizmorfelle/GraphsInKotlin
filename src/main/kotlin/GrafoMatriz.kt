class GrafoMatriz(override var direcionado: Boolean = true, override var ponderado: Boolean = true) : Grafo() {
    override fun imprimeGrafo() {
        print("  ")
        vertices.forEach { (label) -> print("$label ") }
        println()
        for (verticeX in vertices) {
            print("${verticeX.nome} ")
            for (verticeY in vertices) {
                if (verticeX.vizinhos.containsKey(verticeY)) print("1 ")
                else print("0 ")
            }
            println()
        }
    }
}