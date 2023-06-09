import java.lang.Exception
import java.nio.file.Paths

class Leitor {
    public fun getGrafoByFile(fileName: String): Grafo? {
        try {
            val grafo = Grafo()

            Paths.get("src/main/resources/$fileName.txt").toFile().forEachLine {
                if (it.isNotBlank()) {
                    val stringSplit = it.split(" ")

                    if (stringSplit.size < 2 || stringSplit.size > 4) throw Exception("Arquivo com formato inválido!\nErro na linha: $it")

                    if (stringSplit.size == 4) {
                        for (i in 0 until stringSplit[0].toInt()) {
                            grafo.adicionarVertice(i.toString())
                        }
                        grafo.direcionado = stringSplit[2] == "1"
                        grafo.ponderado = stringSplit[3] == "1"
                    } else {
                        if (stringSplit.size == 2)
                            grafo.inserirAresta(stringSplit[0].toInt(), stringSplit[1].toInt(), 1)
                        else
                            grafo.inserirAresta(stringSplit[0].toInt(), stringSplit[1].toInt(), stringSplit[2].toInt())
                    }
                }

            }

            return grafo
        } catch (e: Exception) {
            println(e)
            return null
        }
    }
}