import org.example.GraphDW
import org.example.fordFulkerson
import org.example.edmondKarps

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Ford_fulkersonKtTest {

    //Test basic graph of sink and source
    @Test
    fun FordtwoNodes(){
        var x = GraphDW<String>();

        x.addVertices("a")
        x.addVertices("b")


        x.addEdge("a", "b", 10)


        assertEquals(10, fordFulkerson(x, "a", "b"))
        //assertEquals(10, edmondKarps(x, "a", "b"))
    }

    //test basic graph with sink and source
    @Test
    fun EdmondtwoNodes(){
        var x = GraphDW<String>();

        x.addVertices("a")
        x.addVertices("b")


        x.addEdge("a", "b", 10)


        assertEquals(10, edmondKarps(x, "a", "b"))
    }

    //Test graph with floating nodes for Ford Fulkerson
    @Test
    fun FordfloatingNodes() {
        var x = GraphDW<String>();
        x.addVertices("a")
        x.addVertices("b")
        x.addVertices("c")
        x.addVertices("d")
        x.addVertices("e")
        x.addVertices("f")
        x.addEdge("a", "b", 10)
        x.addEdge("b", "c", 25)
        x.addEdge("c", "f", 10)
        x.addEdge("a", "d", 10)
        x.addEdge("d", "e", 15)
        assertEquals(10, fordFulkerson(x, "a", "f"))
    }

    //Test graph with floating nodes for Edmond
    @Test
    fun EdmondfloatingNodes() {
        var x = GraphDW<String>();
        x.addVertices("a")
        x.addVertices("b")
        x.addVertices("c")
        x.addVertices("d")
        x.addVertices("e")
        x.addVertices("f")
        x.addEdge("a", "b", 10)
        x.addEdge("b", "c", 25)
        x.addEdge("c", "f", 10)
        x.addEdge("a", "d", 10)
        x.addEdge("d", "e", 15)
        assertEquals(10, edmondKarps(x, "a", "f"))
    }



    //Tests that the ford fulkerson works correctly given graphs with known max flows
    @Test
    fun testFordFulkerson() {
        var x = GraphDW<String>();
        x.addVertices("a")
        x.addVertices("b")
        x.addVertices("c")
        x.addVertices("d")
        x.addVertices("e")
        x.addVertices("f")
        x.addEdge("a", "b", 10)
        x.addEdge("b", "c", 25)
        x.addEdge("c", "f", 10)
        x.addEdge("a", "d", 10)
        x.addEdge("d", "e", 15)
        x.addEdge("e", "f", 10)
        x.addEdge("e", "b", 6)
        assertEquals(20, fordFulkerson(x, "a", "f"))
    }

    //Tests that edmond karps works on a graph with a known maximum flow
    @Test
    fun testEdmondKarps() {
        var x = GraphDW<String>();

        x.addVertices("a")
        x.addVertices("b")
        x.addVertices("c")
        x.addVertices("d")
        x.addVertices("e")
        x.addVertices("f")

        x.addEdge("a", "b", 10)
        x.addEdge("b", "c", 25)
        x.addEdge("c", "f", 10)
        x.addEdge("a", "d", 10)
        x.addEdge("d", "e", 15)
        x.addEdge("e", "f", 10)
        x.addEdge("e", "b", 6)

        assertEquals(20, edmondKarps(x, "a", "f"))
    }

    //Test that Edmond Karps works on a graph with backward edges
    @Test
    fun EdmondcomplexGraph(){
        var x = GraphDW<String>();

        x.addVertices("a")
        x.addVertices("b")
        x.addVertices("c")
        x.addVertices("d")
        x.addVertices("e")
        x.addVertices("f")

        x.addEdge("a", "b", 11)
        x.addEdge("b", "c", 12)
        x.addEdge("c", "f", 19)
        x.addEdge("a", "d", 12)
        x.addEdge("d", "b", 1)
        x.addEdge("d", "e", 11)
        x.addEdge("e", "c", 7)
        x.addEdge("e", "f", 4)

        assertEquals(23, edmondKarps(x, "a", "f"))

    }

    //Test that Ford Fulkerson works on a graph with backward edges
    @Test
    fun FordcomplexGraph(){
        var x = GraphDW<String>();

        x.addVertices("a")
        x.addVertices("b")
        x.addVertices("c")
        x.addVertices("d")
        x.addVertices("e")
        x.addVertices("f")

        x.addEdge("a", "b", 11)
        x.addEdge("b", "c", 12)
        x.addEdge("c", "f", 19)
        x.addEdge("a", "d", 12)
        x.addEdge("d", "b", 1)
        x.addEdge("d", "e", 11)
        x.addEdge("e", "c", 7)
        x.addEdge("e", "f", 4)

        assertEquals(23, fordFulkerson(x, "a", "f"))

    }


    //Test that Ford Fulkerson works on a graph with multiple backward edges and capacity not completely fulfilled
    @Test
    fun FordExtracomplexGraph(){
        var x = GraphDW<String>();

        x.addVertices("a")
        x.addVertices("b")
        x.addVertices("c")
        x.addVertices("d")
        x.addVertices("e")
        x.addVertices("f")

        x.addEdge("a", "b", 16)
        x.addEdge("b", "c", 12)
        x.addEdge("c", "f", 20)
        x.addEdge("a", "d", 13)
        x.addEdge("d", "b", 4)
        x.addEdge("b", "d", 10)
        x.addEdge("c", "d", 9)
        x.addEdge("d", "e", 14)
        x.addEdge("e", "c", 7)
        x.addEdge("e", "f", 4)

        assertEquals(23, fordFulkerson(x, "a", "f"))
    }

    //Test that Edmond-Karps works on a graph with multiple backward edges and capacity not completely fulfilled
    @Test
    fun EdmondExtracomplexGraph(){
        var x = GraphDW<String>();

        x.addVertices("a")
        x.addVertices("b")
        x.addVertices("c")
        x.addVertices("d")
        x.addVertices("e")
        x.addVertices("f")

        x.addEdge("a", "b", 16)
        x.addEdge("b", "c", 12)
        x.addEdge("c", "f", 20)
        x.addEdge("a", "d", 13)
        x.addEdge("d", "b", 4)
        x.addEdge("b", "d", 10)
        x.addEdge("c", "d", 9)
        x.addEdge("d", "e", 14)
        x.addEdge("e", "c", 7)
        x.addEdge("e", "f", 4)

        assertEquals(23, edmondKarps(x, "a", "f"))
    }

}
