package series.serie3;


public class MST {
    private static MinPriorityQueueHeapBased<Edge> pq;
    private static Vertex dest,orig;

    public static boolean isEdgeInAnMST(Vertex[] graph, int origId, int destId){
        dest = null;orig = null;
        if(origId==destId || graph.length==0) return false;
        pq = new MinPriorityQueueHeapBased<>(Edge.class,graph.length*graph.length);
        findID(graph,origId,destId);
        //Começar no indice 0:
        return inMST(graph,origId,destId);
    }

    private static boolean inMST(Vertex[] graph, int origId, int destId) {
        Edge e;
        //Começar no indice 0 :
        Vertex actual=graph[0];
        graph[0].setCurrentWeight(0.0);
        graph[0].setCheck(true);
        e = graph[0].getList();

        while(!allVertexChecked(graph)) {
            while (e != null) {
                if(!invertedArc(e, actual)){
                    pq.insert(e);
                    if(e.getAdjacent().getCurrentWeight()==null || e.getWeight()< e.getAdjacent().getCurrentWeight()){
                        e.getAdjacent().setCurrentWeight(e.getWeight());
                        e.getAdjacent().setPrecedent(actual.getId());
                    }
                    else if(e.getWeight() == e.getAdjacent().getCurrentWeight())
                        e.getAdjacent().setNextPrecedent(actual.getId());
                }
                e = e.getNext();
            }
            do{
                if(pq.getSize()==0) return false;
                e = pq.remove();
            } while (e.getAdjacent().isCheck());
            e.getAdjacent().setCheck(true);
            actual = e.getAdjacent();
            e = actual.getList();
        }
        return verifyEdge(dest,origId) || verifyEdge(orig,destId);
    }

    private static boolean verifyEdge(Vertex dest,int orig) {
        PrecedentNode p = dest.getPrecedent();
        while(p!=null) {
            if (p.value == orig) return true;
            p = p.next;
        }
        return false;
    }

    private static boolean invertedArc(Edge e, Vertex actual) {
        PrecedentNode p = actual.getPrecedent();
        while(p!=null){
            if(p.value == e.getAdjacent().getId()) return true;
            p = p.next;
        }
        return false;
    }


    private static void findID(Vertex[] graph,int origId,int destId) {
        for(int i=0;i<graph.length;++i) {
            if (graph[i].getId() == origId) orig = graph[i];
            if(graph[i].getId() == destId) dest = graph[i];
            if(orig!=null && dest!=null) break;
        }
    }

    private static boolean allVertexChecked(Vertex[] graph) {
        for(int i=0; i<graph.length; ++i)
            if(!graph[i].isCheck()) return false;
        return true;
    }

}
