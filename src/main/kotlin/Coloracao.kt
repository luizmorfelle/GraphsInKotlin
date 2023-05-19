class Coloracao {
    companion object {
        private val vetorCores = List(100000) { it + 1 }

        fun welshPowell(grafo: Grafo) {
            var vertices = grafo.vertices
            vertices = vertices.sortedBy { it.vizinhos.size }.reversed().toMutableList()

            while (vertices.any { it.cor == 0 }) {
                val first = vertices.first { it.cor == 0 }
                val cor = vetorCores.first { first.vizinhos.none {vizinho -> vizinho.key.cor == it } }
                first.cor = cor
            }
        }

        fun dsatur(grafo: Grafo) {
            var vertices = grafo.vertices
            vertices = vertices.sortedBy { it.vizinhos.size }.reversed().toMutableList()

            vertices.first().cor = vetorCores.first()

            while (vertices.any { it.cor == 0 }) {
                vertices = vertices.sortedBy { it.calculaSaturacao() }.reversed().toMutableList()
                val first = vertices.first { it.cor == 0 }
                val cor = vetorCores.first { first.vizinhos.none {vizinho -> vizinho.key.cor == it } }
                first.cor = cor
            }
        }

    }
}