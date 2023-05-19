import java.util.Collections

open class Grafo(open var direcionado: Boolean = true, open var ponderado: Boolean = true) {

    override fun toString(): String {
        return vertices.toString()
    }

    data class Vertice(val nome: String) {
        val vizinhos = mutableMapOf<Vertice, Int>()
        var cor = 0
        var saturacao = 0
        fun calculaSaturacao(): Int {
            return this.vizinhos.map { it.key.cor }.distinct().count { it != 0 }
        }

        override fun toString(): String {
            return "$nome - VIZ: ${vizinhos.size} - COR: $cor - SAT: ${calculaSaturacao()} \n"
        }

    }

    data class Face(val label: String, val vertices: MutableList<Vertice>) {
        override fun toString(): String {
            return label
        }

    }

    val vertices = mutableListOf<Vertice>()

    fun getFaces(): MutableList<Face> {
        val lista = mutableListOf<Face>()
        vertices.forEach { pontoPartida ->
            pontoPartida.vizinhos.forEach { primerioVizinho ->
                primerioVizinho.key.vizinhos.forEach { segundoVizinho ->
                    if (segundoVizinho.key != pontoPartida && segundoVizinho.key.vizinhos.containsKey(pontoPartida)) {
                        val verticesFace = mutableListOf(pontoPartida, primerioVizinho.key, segundoVizinho.key)
                        val face = Face(verticesFace.joinToString(separator = "-") { it.nome }, verticesFace)
                        if (lista.find {
                                it.label.contains(pontoPartida.nome) &&
                                        it.label.contains(primerioVizinho.key.nome) &&
                                        it.label.contains(segundoVizinho.key.nome)
                            } == null) {
                            lista.add(face)
                        }
                    }
                }
            }

        }
        return lista
    }

    operator fun get(nome: String) = vertices.find { it.nome == nome } ?: throw IllegalArgumentException()

    private fun labelVertice(indice: Int): String? {
        for ((index, value) in vertices.withIndex()) {
            if (index == indice) return value.nome
        }
        return null
    }

    fun getVerticeByIndex(indice: Int): Vertice? {
        return vertices[indice]
    }

    fun getIndexByVertice(vertice: Vertice): Int {
        for ((index, value) in vertices.withIndex()) {
            if (value == vertice) return index
        }
        return -1
    }


    open fun imprimeGrafo() {
        for (vertice in vertices) {
            println(vertice.nome + " " + vertice.vizinhos.map { it.key.nome })
        }
    }

    open fun imprimeGrafoLista() {
        println("-----------------------------")
        for (vertice in vertices) {
            println(vertice.nome + " " + vertice.vizinhos.map { it.key.nome })
        }
        println()
    }

    fun imprimeGrafoMatriz() {
        println("-----------------------------")
        print("  ")
        vertices.forEach { (label) -> print("$label ") }
        println()
        for (verticeX in vertices) {
            print("${verticeX.nome} ")
            for (verticeY in vertices) {
                if (verticeX.vizinhos.containsKey(verticeY)) print("1 ")
                else print("0 ")
            }
            println()
        }
        println()
    }

    fun getArestas(): MutableList<MutableMap<Vertice, Vertice>> {
        val arestas = mutableListOf<MutableMap<Vertice, Vertice>>()
        vertices.forEach { origem ->
            origem.vizinhos.forEach { destino ->
                if (
                    arestas.none {
                        (it.containsKey(origem) && it.containsValue(destino.key)) ||
                                (it.containsKey(destino.key) && it.containsValue(origem))
                    }
                ) {
                    arestas.add(mutableMapOf(Pair(origem, destino.key)))
                }
            }
        }
        return arestas
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
        vertices.add(Vertice(nome))
    }

    fun removerVertice(nome: String) {
        val verticeRemover = vertices.find { it.nome == nome }
        vertices.remove(verticeRemover)
        for (vertice in vertices) {
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

    fun k5(): Boolean {

        val lista = mutableListOf<Vertice>()

        for (v1 in vertices) {
            lista.add(v1)
            lista.addAll(v1.vizinhos.keys)
            for (v2 in v1.vizinhos.keys) {
                if (!lista.containsAll(v2.vizinhos.keys)) {
                    continue
                }
                for (v3 in v2.vizinhos.keys) {
                    if (!lista.containsAll(v3.vizinhos.keys)) {
                        continue
                    }
                    for (v4 in v3.vizinhos.keys) {
                        if (!lista.containsAll(v4.vizinhos.keys)) {
                            continue
                        }
                        for (v5 in v4.vizinhos.keys) {
                            if (!lista.containsAll(v5.vizinhos.keys)) {
                                continue
                            }
                            return true
                        }
                    }
                }
            }

        }

        return false
    }

    fun contemSubGrafo3(): Boolean {
        vertices.forEach { pontoPartida ->
            pontoPartida.vizinhos.forEach { primerioVizinho ->
                primerioVizinho.key.vizinhos.forEach { segundoVizinho ->
                    if (segundoVizinho.key != pontoPartida && segundoVizinho.key.vizinhos.containsKey(pontoPartida)) {
                        return true
                    }
                }
            }

        }
        return false
    }

}