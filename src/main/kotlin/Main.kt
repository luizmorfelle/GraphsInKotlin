fun main(args: Array<String>) {
    val g = getGrafo(true) ?: return

//    println(Busca().buscaProfundidade(g,0,12))
//    println(Busca().dijkstra(g,0))
//   println(Euler.getFacesEuler(g))
//    g.getFaces().forEach {
//        println(it.label)
//    }
//    Planaridade.verificaPlanaridade(g)
    Coloracao.welshPowell(g)
    g.vertices.forEach { it.cor = 0 }
    Coloracao.dsatur(g)
//    println(if (g.existeAresta(0, 1)) "Aresta Existe" else "Aresta não existe")
//    println(if (g.existeAresta(0, 100)) "Aresta Existe" else "Aresta não existe") // Index que não existe
//
//    println(g.retornarVizinhos(0))
//    println(g.retornarVizinhos(10000)) // Index que não existe
//    println(g.retornarIndicesVizinhos(0))
//    println(g.retornarIndicesVizinhos(10000)) // Index que não existe
//
//
//    println(g.pesoAresta(0, 1))
//    println(g.pesoAresta(0, 1000)) // Index que não existe = 0
//
//    g.removerVertice("A")
//    g.removerVertice("Z") // nome que não existe
//
//    g.imprimeGrafoLista()
//    g.imprimeGrafoMatriz()
//
//    g.removerAresta(0, 1)
//    g.removerAresta(0, 1000) // index não existe
//
//    g.imprimeGrafoLista()
//    g.imprimeGrafoMatriz()
}

fun getGrafo(byFile: Boolean): Grafo? {
    if (byFile) {
        return Leitor().getGrafoByFile("C4000-260-X")
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