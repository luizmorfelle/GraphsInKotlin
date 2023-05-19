class Euler {
    companion object {
        fun getFacesEuler(grafo: Grafo) : Int{
            return ( 2 - grafo.vertices.size + grafo.getArestas().size)

        }
    }
}