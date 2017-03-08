package series.serie2.aplicaçao;

/**
 * serve para extrair a chave de uma determinado elemento
 * @param <E>
 */
public interface KeyExtractor <E>{
    int getKey(E e);
}
