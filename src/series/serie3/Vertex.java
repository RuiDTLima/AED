package series.serie3;


public class Vertex {
    private int id;
    private Edge list;
    private Double currentWeight;
    private PrecedentNode precedentNode;
    boolean check;

    public Vertex(int id,Edge list){
        this.id = id;
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public Edge getList() {
        return list;
    }

    public Double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public PrecedentNode getPrecedent() {
        return precedentNode;
    }

    public void setPrecedent(int precedent) {
        if (precedentNode==null) precedentNode = new PrecedentNode(precedent);
        else{
            if(precedentNode.next!=null)
                precedentNode = new PrecedentNode(precedent);
            else precedentNode.value = precedent;
        }
    }

    public void setNextPrecedent(int nextPrecedent) {
        PrecedentNode p = precedentNode;
        while(p.next!=null){
            p = p.next;
        }
        p.next = new PrecedentNode(nextPrecedent);
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

}
