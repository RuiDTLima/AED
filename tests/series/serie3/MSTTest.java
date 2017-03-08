package series.serie3;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by rui_l on 06/01/2017.
 */
public class MSTTest {

    private Vertex[] createGraph2() {
        Vertex[] aux = new Vertex[4];

        Edge um_dois = new Edge(aux[0],2);
        Edge um_tres = new Edge(aux[0],3);
        Edge um_quatro = new Edge(aux[0],3);
        Edge dois_um = new Edge(aux[1],2);
        Edge dois_tres = new Edge(aux[1],0);
        Edge dois_quatro = new Edge(aux[1],2);
        Edge tres_um = new Edge(aux[2],3);
        Edge tres_dois = new Edge(aux[2],0);
        Edge tres_quatro = new Edge(aux[2],2);
        Edge quatro_um = new Edge(aux[3],3);
        Edge quatro_dois = new Edge(aux[3],2);
        Edge quatro_tres = new Edge(aux[3],2);

        um_dois.setNext(um_tres);
        um_tres.setNext(um_quatro);
        dois_um.setNext(dois_tres);
        dois_tres.setNext(dois_quatro);
        tres_um.setNext(tres_dois);
        tres_dois.setNext(tres_quatro);
        quatro_um.setNext(quatro_dois);
        quatro_dois.setNext(quatro_tres);

        aux[0] = new Vertex(1,um_dois);
        aux[1] = new Vertex(2,dois_um);
        aux[2] = new Vertex(3,tres_um);
        aux[3] = new Vertex(4,quatro_um);

        um_dois.setAdjacent(aux[1]);
        um_tres.setAdjacent(aux[2]);
        um_quatro.setAdjacent(aux[3]);
        dois_um.setAdjacent(aux[0]);
        dois_tres.setAdjacent(aux[2]);
        dois_quatro.setAdjacent(aux[3]);
        tres_um.setAdjacent(aux[0]);
        tres_dois.setAdjacent(aux[1]);
        tres_quatro.setAdjacent(aux[3]);
        quatro_um.setAdjacent(aux[0]);
        quatro_dois.setAdjacent(aux[1]);
        quatro_tres.setAdjacent(aux[2]);
        return aux;
    }

    private Vertex[] createGraph() {
        Vertex[] aux = new Vertex[5];
        Edge um_dois = new Edge(aux[0],1);
        Edge um_tres = new Edge(aux[0],2);
        Edge dois_um = new Edge(aux[1],1);
        Edge dois_quatro = new Edge(aux[1],3);
        Edge tres_um = new Edge(aux[2],2);
        Edge tres_quatro = new Edge(aux[2],5);
        Edge tres_cinco = new Edge(aux[2],4);
        Edge quatro_dois = new Edge(aux[3],3);
        Edge quatro_tres = new Edge(aux[3],5);
        Edge quatro_cinco = new Edge(aux[3],2);
        Edge cinco_tres = new Edge(aux[4],4);
        Edge cinco_quatro = new Edge(aux[4],2);

        um_dois.setNext(um_tres);
        dois_um.setNext(dois_quatro);
        tres_um.setNext(tres_quatro);
        tres_quatro.setNext(tres_cinco);
        quatro_dois.setNext(quatro_tres);
        quatro_tres.setNext(quatro_cinco);
        cinco_tres.setNext(cinco_quatro);

        aux[0] = new Vertex(1,um_dois);
        aux[1] = new Vertex(2,dois_um);
        aux[2] = new Vertex(3,tres_um);
        aux[3] = new Vertex(4,quatro_dois);
        aux[4] = new Vertex(5,cinco_tres);

        um_dois.setAdjacent(aux[1]);
        um_tres.setAdjacent(aux[2]);
        dois_um.setAdjacent(aux[0]);
        dois_quatro.setAdjacent(aux[3]);
        tres_um.setAdjacent(aux[0]);
        tres_quatro.setAdjacent(aux[3]);
        tres_cinco.setAdjacent(aux[4]);
        quatro_dois.setAdjacent(aux[1]);
        quatro_tres.setAdjacent(aux[2]);
        quatro_cinco.setAdjacent(aux[4]);
        cinco_tres.setAdjacent(aux[2]);
        cinco_quatro.setAdjacent(aux[3]);
        return aux;
    }


    private Vertex[] createGraph3() {
        Vertex[] aux = new Vertex[4];
        Edge um_dois = new Edge(aux[0],1);
        Edge dois_tres = new Edge(aux[1],4);
        Edge dois_quatro = new Edge(aux[1],3);
        Edge dois_um = new Edge(aux[1],1);
        Edge tres_dois = new Edge(aux[2],4);
        Edge quatro_dois = new Edge(aux[3],3);

        dois_um.setNext(dois_tres);
        dois_tres.setNext(dois_quatro);

        aux[0] = new Vertex(1,um_dois);
        aux[1] = new Vertex(2,dois_um);
        aux[2] = new Vertex(3,tres_dois);
        aux[3] = new Vertex(4,quatro_dois);

        um_dois.setAdjacent(aux[1]);
        dois_um.setAdjacent(aux[0]);

        dois_tres.setAdjacent(aux[2]);
        tres_dois.setAdjacent(aux[1]);

        dois_quatro.setAdjacent(aux[3]);
        quatro_dois.setAdjacent(aux[1]);
        return aux;
    }

    private Vertex[] createGraph4() {
        Vertex[] aux = new Vertex[4];

        Edge um_dois = new Edge(aux[0],2);
        Edge um_tres = new Edge(aux[0],2);
        Edge um_quatro = new Edge(aux[0],2);
        Edge dois_um = new Edge(aux[1],2);
        Edge dois_tres = new Edge(aux[1],2);
        Edge dois_quatro = new Edge(aux[1],2);
        Edge tres_um = new Edge(aux[2],2);
        Edge tres_dois = new Edge(aux[2],2);
        Edge tres_quatro = new Edge(aux[2],2);
        Edge quatro_um = new Edge(aux[3],2);
        Edge quatro_dois = new Edge(aux[3],2);
        Edge quatro_tres = new Edge(aux[3],2);

        um_dois.setNext(um_tres);
        um_tres.setNext(um_quatro);
        dois_um.setNext(dois_tres);
        dois_tres.setNext(dois_quatro);
        tres_um.setNext(tres_dois);
        tres_dois.setNext(tres_quatro);
        quatro_um.setNext(quatro_dois);
        quatro_dois.setNext(quatro_tres);

        aux[0] = new Vertex(1,um_dois);
        aux[1] = new Vertex(2,dois_um);
        aux[2] = new Vertex(3,tres_um);
        aux[3] = new Vertex(4,quatro_um);

        um_dois.setAdjacent(aux[1]);
        um_tres.setAdjacent(aux[2]);
        um_quatro.setAdjacent(aux[3]);
        dois_um.setAdjacent(aux[0]);
        dois_tres.setAdjacent(aux[2]);
        dois_quatro.setAdjacent(aux[3]);
        tres_um.setAdjacent(aux[0]);
        tres_dois.setAdjacent(aux[1]);
        tres_quatro.setAdjacent(aux[3]);
        quatro_um.setAdjacent(aux[0]);
        quatro_dois.setAdjacent(aux[1]);
        quatro_tres.setAdjacent(aux[2]);
        return aux;
    }

    @Test
    public void TesteDiscussão() throws Exception {
        Vertex[] aux = createGraph4();
        assertTrue(MST.isEdgeInAnMST(aux,3,4) && MST.isEdgeInAnMST(aux,1,3));
    }

/*@Test
    public void InvalidVertex() throws Exception {
        Vertex[] aux = createGraph();
        assertFalse(MST.isEdgeInAnMST(aux,6,1));
    }*/

    @Test
    public void EmptyGraph() throws Exception {
        Vertex[] aux = new Vertex[0];
        assertFalse(MST.isEdgeInAnMST(aux,1,5));
    }

    @Test
    public void OneMST() throws Exception {
        Vertex[] aux = createGraph3();
        assertTrue(MST.isEdgeInAnMST(aux,1,2));
    }

    @Test
    public void Graph2_EdgeNotMST() throws Exception {
        Vertex[] aux = createGraph2();
        assertFalse(MST.isEdgeInAnMST(aux,4,1));
    }

    @Test
    public void Graph2_EdgeOnMST() throws Exception {
        Vertex[] aux = createGraph2();
        assertTrue(MST.isEdgeInAnMST(aux,4,2) && MST.isEdgeInAnMST(aux,3,4));
    }
    @Test
    public void Graph1_EdgeNotMST() throws Exception{
        Vertex[] aux = createGraph();
        assertFalse(MST.isEdgeInAnMST(aux,3,4));
    }

    @Test
    public void Graph1_EdgeOnMST() throws Exception {
        Vertex[] aux = createGraph();
        assertTrue(MST.isEdgeInAnMST(aux,1,2));
    }

    @Test
    public void NotAnEdge() throws Exception {
        Vertex[] aux = createGraph();
        assertFalse(MST.isEdgeInAnMST(aux,3,3));
    }
}
