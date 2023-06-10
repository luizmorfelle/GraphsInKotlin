class FordFukerson {
    companion object {
        private var fluxoMaximo = 0

        fun main(grafo: Grafo, origem: Int, destino: Int): Int {
            val novoGrafo = grafo.clone()

            var caminho = Busca().buscaProfundidadeMenorCaminho(novoGrafo, origem, destino)

            while (caminho.isNotEmpty()) {
                val minimo = caminho.minOfOrNull {
                    it.first.vizinhos[it.second] ?: Int.MAX_VALUE
                } ?: 0
                fluxoMaximo += minimo

                for (pair in caminho) {
                    pair.first.vizinhos[pair.second] = pair.first.vizinhos[pair.second]!! - minimo
                    pair.second.vizinhos[pair.first] = (pair.second.vizinhos[pair.first] ?: 0) + minimo
                }
                novoGrafo.getArestas().filter { it.first.vizinhos[it.second] ==0  }.forEach {
                    it.first.vizinhos.remove(it.second)
                }
                caminho = Busca().buscaProfundidadeMenorCaminho(novoGrafo, origem, destino)
            }
            return fluxoMaximo
        }
    }
}


//                caminho.forEachIndexed { index, vertice ->
//                    if (index != caminho.size - 1) {
//                        val peso = vertice.vizinhos[caminho.elementAt(index + 1)] ?: 0
//                        minimo =  if (peso < minimo) peso else minimo
//                    }
//                }