package series.serie3;

public class TrieNode {
    private TrieNode[] t = new TrieNode[4];
    private boolean word;
    private int prefixOf = 0;

    public TrieNode(){}

    public TrieNode(int prefix){
        prefixOf = prefix;
    }

    public void setT(int idx) {
        t[idx]=new TrieNode(1);
    }

    public TrieNode getT(int idx) {
        return t[idx];
    }

    public void setWordTrue() {
        word = true;
    }

    public int getPrefixOf() {
        return prefixOf;
    }

    public void setPrefixOf(int prefixOf) {
        this.prefixOf = prefixOf;
    }
}
