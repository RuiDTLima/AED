package series.serie3;

public class DNACollection {
    private TrieNode trie;
    private final char[] ALPHABET = {'A','C','G','T'};

    public DNACollection(){
        trie = new TrieNode();
    }

    //Complexidade Temporal: O(N) N=fragment.length
    public void add(String fragment) {
        TrieNode aux = trie;
        char c;
        for(int i=0;i<fragment.length();++i){
            c = fragment.charAt(i);
            if(getLetterIndex(c)==ALPHABET.length) return;
            int idx = getLetterIndex(c);
            if(aux.getT(idx)==null){
                aux.setT(idx);
                aux = aux.getT(idx);
            }
            else {
                aux = aux.getT(idx);
                aux.setPrefixOf(aux.getPrefixOf() + 1);
            }
        }
        aux.setWordTrue();
    }

    //Complexidade Temporal: O(N) N=p.length
    public int prefixCount(String p) {
        TrieNode aux = trie;
        char c;
        for (int i = 0; i < p.length(); ++i){
            c = p.charAt(i);
            int idx = getLetterIndex(c);
            if(idx==ALPHABET.length || aux.getT(idx)==null) return 0;
            aux = aux.getT(idx);
        }
        return aux.getPrefixOf();
    }

    public int getLetterIndex(char c){
        for(int i= 0;i<ALPHABET.length;++i)
            if(c==ALPHABET[i]) return i;
        return ALPHABET.length;
    }
}
