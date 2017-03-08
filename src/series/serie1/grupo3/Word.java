package series.serie1.grupo3;
/**
 * Created by rui_l on 26/10/2016.
 */
public class Word {
    private String name;
    private int amount;

    public Word(String word){
        name = word;
        amount = 1;
    }

    /**
     * Incrementa o nº de repetições de uma dada palavra
     */
    public void increaseAmount(){
        ++amount;
    }

    /**
     * @return // retorna a palavra
     */
    public String getName() {
        return name;
    }

    /**
     * @return // retorna o nº de repetições de uma palavra
     */
    public int getAmount() {
        return amount;
    }
}
