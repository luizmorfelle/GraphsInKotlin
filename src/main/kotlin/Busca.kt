class Busca {

    private var visitados = mutableSetOf<String>()

    fun buscaProfundidade(grafo: Grafo, indexOrigem: Int, indexDestino: Int): MutableSet<String> {
        val verticeOrigem = grafo.getVerticeByIndex(indexOrigem) ?: return visitados

        visitados.add(verticeOrigem.nome)

        if (indexOrigem == indexDestino || visitados.size == grafo.vertices.size) return visitados

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
        val verticeOrigem = grafo.getVerticeByIndex(indexOrigem) ?: return visitados
        val filaVertices = mutableListOf<Grafo.Vertice>()
        visitados.add(verticeOrigem.nome)

        filaVertices.add(verticeOrigem)

        while (filaVertices.isNotEmpty()) {
            val vertice = filaVertices.removeAt(0)
            for (vizinho in vertice.vizinhos.keys) {
                if (!visitados.contains(vizinho.nome)) {
                    visitados.add(vizinho.nome)
                    if (grafo.getIndexByVertice(vizinho) == indexDestino || visitados.size == grafo.vertices.size) return visitados
                    filaVertices.add(vizinho)
                }
            }
        }
        return visitados
    }

    //dijkstra
    fun dijkstra(grafo: Grafo, indexOrigem: Int): MutableMap<Grafo.Vertice, Int>? {

        val verticeOrigem = grafo.getVerticeByIndex(indexOrigem) ?: return null
        val distancias = mutableMapOf<Grafo.Vertice, Int>()
        val anteriores = mutableMapOf<Grafo.Vertice, Grafo.Vertice>()

        for (vertice in grafo.vertices) {
            distancias[vertice] = Int.MAX_VALUE
        }

        distancias[verticeOrigem] = 0

        while (visitados.size != grafo.vertices.size) {
            val mapMinVertice = distancias
                .filter { !visitados.contains(it.key.nome) }
                .minBy { it.value }

            visitados.add(mapMinVertice.key.nome)

            for (vizinho in mapMinVertice.key.vizinhos) {
                val distanciaNova: Int = distancias[mapMinVertice.key]?.plus(vizinho.value) ?: continue
                if (distancias[vizinho.key]!! > distanciaNova) {
                    distancias[vizinho.key] = distanciaNova
                    anteriores[vizinho.key] = mapMinVertice.key
                }
            }

        }

        println(anteriores)
        return distancias
    }


}