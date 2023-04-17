class Busca {

    private var visitados = mutableSetOf<String>()

    fun buscaProfundidade(grafo: Grafo, indexOrigem: Int, indexDestino: Int): MutableSet<String> {
        val verticeOrigem = grafo.getVerticeByIndex(indexOrigem)
        val verticeDestino = grafo.getVerticeByIndex(indexDestino)

        if (verticeOrigem == null || verticeDestino == null) return visitados
        visitados.add(verticeOrigem.nome)

        if (indexOrigem == indexDestino) return visitados

        val vizinhosNaoVisitados = verticeOrigem.vizinhos.filter { !visitados.contains(it.key.nome) }

        if (vizinhosNaoVisitados.isEmpty())
            return buscaProfundidade(
                grafo,
                grafo.getIndexByVertice(verticeOrigem.vizinhos.keys.minBy { grafo.getIndexByVertice(it) }),
                indexDestino
            )

        val indexNextVertice = grafo.getIndexByVertice(vizinhosNaoVisitados.keys.first())
        return buscaProfundidade(grafo, indexNextVertice, indexDestino)
    }

    fun buscaLargura(grafo: Grafo, indexOrigem: Int, indexDestino: Int): MutableSet<String> {
        val verticeOrigem = grafo.getVerticeByIndex(indexOrigem)
        val verticeDestino = grafo.getVerticeByIndex(indexDestino)

        if (verticeOrigem == null || verticeDestino == null) return visitados
        return visitados
    }
}