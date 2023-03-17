open class Grafo(open val direcionado: Boolean = true, open val ponderado: Boolean = true) {


    data class Vertice(val nome: String) {
        val vizinhos = mutableMapOf<Vertice, Int>()
        override fun toString(): String {
            return nome
        }

    }

    val vertices = mutableMapOf<String, Vertice>()

    private operator fun get(nome: String) = vertices[nome] ?: throw IllegalArgumentException()

    private fun labelVertice(indice: Int): String? {
        for ((index, value) in vertices.values.withIndex()) {
            if (index == indice) return value.nome
        }
        return null
    }

    private fun getVerticeByIndex(indice: Int): Vertice? {
        return vertices[labelVertice(indice)]
    }

    private fun getIndexByVertice(vertice: Vertice): Int {
        for ((index, value) in vertices.values.withIndex()) {
            if (value == vertice) return index
        }
        return -1
    }

    fun vizinhos(name: String) = vertices[name]?.vizinhos?.map { it.key.nome } ?: listOf()

    open fun imprimeGrafo() {
        for (vertice in vertices) {
            println(vertice.key + " " + vertice.value.vizinhos.map { it.key.nome })
        }
    }

    open fun imprimeGrafoLista() {
        println("-----------------------------")
        for (vertice in vertices) {
            println(vertice.key + " " + vertice.value.vizinhos.map { it.key.nome })
        }
        println()
    }

    fun imprimeGrafoMatriz() {
        println("-----------------------------")
        print("  ")
        vertices.forEach { (label, _) -> print("$label ") }
        println()
        for (verticeX in vertices) {
            print("${verticeX.key} ")
            for (verticeY in vertices) {
                if (verticeX.value.vizinhos.containsKey(verticeY.value)) print("1 ")
                else print("0 ")
            }
            println()
        }
        println()
    }

    fun inserirAresta(origem: Int, destino: Int, peso: Int = 1) {
        val verOrigem = getVerticeByIndex(origem)
        val verDestino = getVerticeByIndex(destino)
        if (verDestino == null || verOrigem == null) return
        if (origem == destino && !direcionado) return
        var newPeso = peso
        if (!ponderado) newPeso = 1

        verOrigem.vizinhos[verDestino] = newPeso
        if (!direcionado) verDestino.vizinhos[verOrigem] = newPeso
    }

    fun removerAresta(origem: Int, destino: Int) {
        val verOrigem = getVerticeByIndex(origem)
        val verDestino = getVerticeByIndex(destino)
        if (verDestino == null || verOrigem == null) return

        verOrigem.vizinhos.remove(verDestino)
        if (!direcionado) verDestino.vizinhos.remove(verOrigem)
    }

    fun adicionarVertice(nome: String) {
        vertices[nome] = Vertice(nome)
    }

    fun removerVertice(nome: String) {
        val verticeRemover = vertices[nome]
        vertices.remove(nome)
        for (vertice in vertices.values) {
            vertice.vizinhos.remove(verticeRemover)
        }
    }

    fun existeAresta(origem: Int, destino: Int): Boolean {
        val verOrigem = getVerticeByIndex(origem)
        val verDestino = getVerticeByIndex(destino)
        if (verDestino == null || verOrigem == null) return false

        return verOrigem.vizinhos.contains(verDestino) && verDestino.vizinhos.contains(verOrigem)
    }

    fun pesoAresta(origem: Int, destino: Int): Int {
        val verOrigem = getVerticeByIndex(origem)
        val verDestino = getVerticeByIndex(destino)

        if (verDestino == null || verOrigem == null) return 0

        return verOrigem.vizinhos[verDestino] ?: 0
    }

    fun retornarVizinhos(indice: Int): MutableSet<Vertice> {
        val vertice = getVerticeByIndex(indice) ?: return mutableSetOf()
        return vertice.vizinhos.keys
    }

    fun retornarIndicesVizinhos(indice: Int): List<Int> {
        val vertice = getVerticeByIndex(indice) ?: return mutableListOf()
        return vertice.vizinhos.keys.map { getIndexByVertice(it) }
    }
}