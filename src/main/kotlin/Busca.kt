class Busca {

    private var visitados = mutableSetOf<Grafo.Vertice>()
    private var caminho = mutableListOf<Pair<Grafo.Vertice, Grafo.Vertice>>()

    fun buscaProfundidade(
        grafo: Grafo,
        indexOrigem: Int,
        indexDestino: Int,
        anterior: Grafo.Vertice? = null
    ): MutableList<Pair<Grafo.Vertice, Grafo.Vertice>> {
        try {
            val verticeOrigem = grafo.getVerticeByIndex(indexOrigem) ?: return caminho

            visitados.add(verticeOrigem)
            if (anterior != null) caminho.add(Pair(anterior, verticeOrigem))
            if (indexOrigem == indexDestino || visitados.size == grafo.vertices.size) return caminho

            val vizinhosNaoVisitados = verticeOrigem.vizinhos.filter { !visitados.contains(it.key) }

            if (vizinhosNaoVisitados.isEmpty())
                return buscaProfundidade(
                    grafo,
                    grafo.getIndexByVertice(verticeOrigem.vizinhos.keys.minBy { grafo.getIndexByVertice(it) }),
                    indexDestino,
                    null
                )

            val indexNextVertice = grafo.getIndexByVertice(vizinhosNaoVisitados.keys.first())
            return buscaProfundidade(grafo, indexNextVertice, indexDestino, verticeOrigem)
        } catch (e: Exception) {
            return mutableListOf();
        }

    }

    fun buscaLargura(grafo: Grafo, indexOrigem: Int, indexDestino: Int): MutableSet<Grafo.Vertice> {
        val verticeOrigem = grafo.getVerticeByIndex(indexOrigem) ?: return visitados
        val filaVertices = mutableListOf<Grafo.Vertice>()
        visitados.add(verticeOrigem)

        filaVertices.add(verticeOrigem)

        while (filaVertices.isNotEmpty()) {
            val vertice = filaVertices.removeAt(0)
            for (vizinho in vertice.vizinhos.keys) {
                if (!visitados.contains(vizinho)) {
                    visitados.add(vizinho)
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
                .filter { !visitados.contains(it.key) }
                .minBy { it.value }

            visitados.add(mapMinVertice.key)

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

    fun buscaProfundidadeMenorCaminho(
        grafo: Grafo,
        indexOrigem: Int,
        indexDestino: Int,
        anterior: Grafo.Vertice? = null
    ): MutableList<Pair<Grafo.Vertice, Grafo.Vertice>> {
        try {
            val verticeOrigem = grafo.getVerticeByIndex(indexOrigem) ?: return caminho
            if (anterior != null && visitados.contains(verticeOrigem)) return mutableListOf()

            visitados.add(verticeOrigem)
            if (anterior != null) caminho.add(Pair(anterior, verticeOrigem))
            if (indexOrigem == indexDestino || visitados.size == grafo.vertices.size) return caminho

            val vizinhosNaoVisitados = verticeOrigem.vizinhos.filter { !visitados.contains(it.key) }

            if (vizinhosNaoVisitados.isEmpty())
                return buscaProfundidade(
                    grafo,
                    grafo.getIndexByVertice(verticeOrigem.vizinhos.keys.filter { it != anterior }.minBy { grafo.getIndexByVertice(it) }),
                    indexDestino,
                    null
                )

            val indexNextVertice = grafo.getIndexByVertice(vizinhosNaoVisitados.keys.filter { it != anterior }.minBy { verticeOrigem.vizinhos[it] ?: Int.MAX_VALUE })
            return buscaProfundidade(grafo, indexNextVertice, indexDestino, verticeOrigem)
        } catch (e: Exception) {
            return mutableListOf();
        }

    }

}