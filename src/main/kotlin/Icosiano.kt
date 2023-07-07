class Icosiano(private val grafo: Grafo) {

    fun findHamiltonianPath(): Boolean {
        val numVertices = grafo.vertices.size
        val path = IntArray(numVertices) { -1 }

        path[0] = 0
        if (!findPathUtil(1, path)) {
            println("Não há caminho hamiltoniano no grafo.")
            return false
        }

        printPath(path)
        return true
    }

    private fun findPathUtil(position: Int, path: IntArray): Boolean {
        val numVertices = grafo.vertices.size

        if (position == numVertices) {
            // Verificar se o último vértice está conectado ao primeiro
            val lastVertex = grafo.vertices[path[position - 1]]
            val firstVertex = grafo.vertices[path[0]]
            return lastVertex.vizinhos.contains(firstVertex)
        }

        for (v in 1 until numVertices) {
            val vertex = grafo.vertices[v]
            if (isSafe(vertex, position, path)) {
                path[position] = v
                if (findPathUtil(position + 1, path)) {
                    return true
                }
                path[position] = -1
            }
        }

        return false
    }

    private fun isSafe(vertex: Grafo.Vertice, position: Int, path: IntArray): Boolean {
        val numVertices = grafo.vertices.size

        val lastVertex = grafo.vertices[path[position - 1]]
        if (!lastVertex.vizinhos.contains(vertex)) {
            return false
        }
        return !path.contains(grafo.getIndexByVertice(vertex))
    }


    private fun printPath(path: IntArray) {
        println("Caminho Hamiltoniano encontrado:")
        for (i in path.indices) {
            print(grafo.vertices[path[i]].nome)
            if (i < path.size - 1) {
                print(" -> ")
            }
        }
        println()
    }

}

