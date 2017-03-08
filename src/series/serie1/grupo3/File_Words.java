package series.serie1.grupo3;


public class File_Words {
    private String s;
    private int fileOrder;

    public File_Words(String s, int fileOrder){
        this.s = s;
        this.fileOrder = fileOrder;
    }

    /**
     * @return // retorna o nome do elemento de um ficheiro
     */
    public String getString() {
        return s;
    }

    /**
     * @return // retorna a ordem com a qual um ficheiro foi passado como argumento à aplicação
     */
    public int getFileOrder() {
        return fileOrder;
    }

    /**
     * altera o valor da palavra a ser analisada de um ficheiro
     * @param currentWord
     */
    public void setString(String currentWord) {
        s = currentWord;
    }
}
