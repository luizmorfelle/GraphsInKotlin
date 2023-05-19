class Planaridade {
    companion object {
        fun verificaPlanaridade(grafo: Grafo) {
            println("--- Planaridade ---")
            val startTime = System.currentTimeMillis()
            if (grafo.vertices.size <= 2) {
                println("É Planar")
            }

            if (grafo.contemSubGrafo3()) {
                if (grafo.getArestas().size <= (grafo.vertices.size * 3 - 6)) {
                    println("Possívelmente Planar")
                } else {
                    println("Não é planar")
                }
            } else {

                if (grafo.getArestas().size <= (grafo.vertices.size * 2 - 4)) {
                    println("Possívelmente Planar")
                } else {
                    println("Não é planar")
                }
            }
            val endTime = System.currentTimeMillis()
            println("Tempo: ${endTime - startTime} ms")
        }

    }
}