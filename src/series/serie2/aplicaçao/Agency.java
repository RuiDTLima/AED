package series.serie2.aplicaçao;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Agency {
    private static PriorityQueue<Customer,Integer> pq = new PriorityQueue<>(100, c->c.getTicket());
    private static int ticket = 0;

    /**
     * Array que indica o tempo de atendimento de cada Serviço
     */
    private static int [] times = {0,2,5,10,20};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int selectedKey,key,id,employees;
        String service;
        boolean close = false;
        while(!close){
            System.out.println("Escolha um comando(tecla 7 : Ajuda) ");
            selectedKey = in.nextInt();
            switch(selectedKey){
                case 1:
                    System.out.println("Número de Identificação: ");
                    id = in.nextInt();
                    do {
                        System.out.println("Serviço: ");
                        service = in.next();
                    }while (!testValid(service));
                    newCustomer(id,Services.valueOf(service));
                    break;
                case 2:
                    do {
                        System.out.println("Numero de senha: ");
                        key = in.nextInt();
                    }while (key>=ticket || key < 0);
                    removeCostumer(key);
                    break;
                case 3:
                    removeNextCostumer();
                    break;
                case 4:
                    getNextCostumer();
                    break;
                case 5:
                    do {
                        System.out.println("Numero de senha: ");
                        key = in.nextInt();
                    }while (key>=ticket || key < 0);
                    System.out.println("Serviço: ");
                    service = in.next();
                    changeService(key,Services.valueOf(service));
                    break;
                case 6:
                    do {
                        System.out.println("Numero de senha: ");
                        key = in.nextInt();
                    }while (key>=ticket || key < 0);
                    do {
                        System.out.println("Numero de funcionarios: ");
                        employees = in.nextInt();
                    }while (employees<=0);
                    waitingTime(key,employees);
                    break;
                case 7:
                    help();
                    break;
                case 8:
                    close = true;
                    break;
                default:
                    System.out.println("Tecla Inválida");
            }
        }
    }

    /**
     * testa se um serviço dado é valido
     * @param service serviço que dado
     * @return true se o serviço pertence ao conjunto de serviços ofericidos pela Agencia, retorna false caso contrario
     */
    private static boolean testValid(String service) {
        for (int i =0; i < Services.values().length; i++){
            if (service.equals(Services.values()[i].toString()))
                return true;
        }
        return false;
    }

    /**
     * apresenta na linha de comandos o conjunto de instruções disponiveis e como as usar
     */
    private static void help() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Comandos de Execução.txt"));
            String s;
            while((s=reader.readLine())!=null){
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * adiciona um novo cliente à priority Queue.
     * @param id
     * @param service
     */
    private static void newCustomer(int id,Services service){
        pq.add(new Customer(id,ticket++),service.ordinal()+1);
    }

    /**
     * remove o cliente identificado por key
     * @param key
     */
    private static void removeCostumer(int key){
        pq.remove(key);
    }

    /**
     * retira da Priority Queue o próximo cliente a ser atendido
     */
    private static void removeNextCostumer(){
        pq.poll();
    }

    /**
     * Indica qual a senha do proximo cliente a ser atendido
     */
    private static void getNextCostumer(){
        if(pq.pick()==null) System.out.println("Sem clientes. ");
        else System.out.println("Senha nº" + pq.pick().getTicket());
    }

    /**
     * altera o serviço do cliente identificado por key
     * @param key  //chave que identifica o cliente
     * @param newService  //novo serviço para o cliente identificado por key
     */
    private static void changeService (int key, Services newService){
        pq.update(key,newService.ordinal()+1);
    }

    /**
     * indica quanto tempo tem de esperar o cliente identificado por key, dado um número de funcionarios
     * @param key  // chave que identifica o cliente
     * @param employees  // número de empregados na agencia
     */
    private static void waitingTime(int key,int employees){
        ArrayList heap = pq.getHeap();
        HashTable hash = pq.getHash();
        Pair<Customer,Integer> aux =  (Pair<Customer, Integer>) hash.search(key).getValue();
        int idx = aux.getValue();
        int time = 0;
        int count = 0;
        int quickest = 20;
        Pair<Customer,Integer> pair = (Pair<Customer,Integer>)heap.get(idx);
        Integer prio = pair.getValue();
        for(Object o : heap) {
            Pair<Customer,Integer> p = (Pair<Customer, Integer>) o;
            if (less(p.getValue(), prio) || p.getValue().equals(prio) && less(pq.getExtract().getKey(p.getKey()), key)) {
                time += times[p.getValue()];
                ++count;
                if (times[p.getValue()] < quickest) quickest = times[p.getValue()];
            }
        }
        if(count==employees) time = quickest;
        else if(count<employees) time = 0;
        System.out.println("Tempo estimado de espera: " + time + " minutos.");
    }

    //método auxiliar
    /**
     * verifica se um elemento é menor que o outro
     * @param v   //primeiro elemento
     * @param w   //segundo elemento
     * @return   //-1 se v é menor que w, 0 se são iguais e 1 se v é maior que w
     */
    private static boolean less(Integer v, Integer w) {
        return v.compareTo(w)<0;
    }
}