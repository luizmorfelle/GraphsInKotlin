class Planaridade {
    companion object {
        fun verificaPlanaridade(grafo: Grafo): Boolean {
            if (grafo.vertices.size <= 2) return true

            return if (grafo.contemSubGrafo3()) {
                grafo.getArestas().size <= (grafo.vertices.size * 3 - 6)
            } else {
                grafo.getArestas().size <= (grafo.vertices.size * 2 - 4)
            }
        }

    }
}