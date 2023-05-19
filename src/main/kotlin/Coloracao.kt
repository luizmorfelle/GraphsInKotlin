class Coloracao {
    companion object {
        private val vetorCores = List(100000) { it + 1 }

        fun welshPowell(grafo: Grafo) {
            println("--- Welsh e Powell ---")
            val startTime = System.currentTimeMillis()
            var vertices = grafo.vertices
            vertices = vertices.sortedBy { it.vizinhos.size }.reversed().toMutableList()

            while (vertices.any { it.cor == 0 }) {
                val first = vertices.first { it.cor == 0 }
                val cor = vetorCores.first { first.vizinhos.none {vizinho -> vizinho.key.cor == it } }
                first.cor = cor
            }
            val endTime = System.currentTimeMillis()
            println("Cores Utilizadas: ${vertices.maxBy { it.cor }.cor}")
            println("Tempo: ${endTime - startTime} ms")
        }

        fun dsatur(grafo: Grafo) {
            println("--- DSATUR ---")
            val startTime = System.currentTimeMillis()

            var vertices = grafo.vertices
            vertices = vertices.sortedBy { it.vizinhos.size }.reversed().toMutableList()

            vertices.first().cor = vetorCores.first()
            vertices.first().vizinhos.keys.forEach { it.saturacao = it.calculaSaturacao() }

            while (vertices.any { it.cor == 0 }) {
                vertices = vertices.sortedBy { it.saturacao }.reversed().toMutableList()
                val first = vertices.first { it.cor == 0 }
                val cor = vetorCores.first { first.vizinhos.none {vizinho -> vizinho.key.cor == it } }
                first.cor = cor
                first.vizinhos.keys.forEach { it.saturacao = it.calculaSaturacao() }
            }

            val endTime = System.currentTimeMillis()
            println("Cores Utilizadas: ${vertices.maxBy { it.cor }.cor}")
            println("Tempo: ${endTime - startTime} ms")
        }

    }
}