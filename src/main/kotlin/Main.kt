fun main(args: Array<String>) {
    val g = getGrafo(true) ?: return
    g.imprimeGrafo()
    val icosiano = Icosiano(g)
    icosiano.findHamiltonianPath()


}

fun getGrafo(byFile: Boolean): Grafo? {
    if (byFile) {
        return Leitor().getGrafoByFile("icosiano")
    }
    val g = Grafo(direcionado = false, ponderado = false)
    g.adicionarVertice("A") //Inserir Vértice
    g.adicionarVertice("B")
    g.adicionarVertice("C")
    g.adicionarVertice("D")
    g.adicionarVertice("E")
    g.adicionarVertice("F")
    g.adicionarVertice("G")
    g.adicionarVertice("G") // Inserção Duplicada

    g.inserirAresta(0, 1, 2) // Inserir Aresta
    g.inserirAresta(1, 2)
    g.inserirAresta(1, 3)
    g.inserirAresta(3, 2)
    g.inserirAresta(3, 2)
    g.inserirAresta(2, 4)
    g.inserirAresta(4, 2)
    g.inserirAresta(1, 100) // Index que não existe
    g.inserirAresta(10000, 100) // Index que não existe
    g.inserirAresta(4, 1) // Arco pra si mesmo, caso seja direcionado

    return g
}