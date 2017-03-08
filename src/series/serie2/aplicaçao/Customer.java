package series.serie2.aplicaçao;


public class Customer {
    /**
     * elemento de identificação de um cliente.
     */
    private int id;
    /**
     * número da senha de um dado cliente.
     */
    private int ticket;

    /**
     * construtor de um customer
     * @param id número de identificação do cliente
     * @param ticket número da senha do cliente
     */
    public Customer(int id, int ticket){
        this.id = id;
        this.ticket = ticket;
    }

    /**
     *
     * @return o número da senha do cliente.
     */
    public int getTicket() {
        return ticket;
    }
}