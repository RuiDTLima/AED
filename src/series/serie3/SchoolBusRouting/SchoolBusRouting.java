package series.serie3.SchoolBusRouting;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SchoolBusRouting {
    private static Node[] adjList;
    private static Node[] schools;
    private static boolean[] visited;
    private static int newIdx,initialId;
    private static GenericStack s;


    public static void main(String[] args) {
        boolean end = false;
        try {
            Scanner scan = new Scanner(new FileReader(args[0]));
            readGraph(scan);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(!end) {
            Scanner in = new Scanner(System.in);
            System.out.println("\nInsira um comando():\n\ts-SchoolPath fileName.s\n\te-Terminar Programa");
            char cmd = in.next().charAt(0);
            switch (cmd) {
                case 's':
                    System.out.println("Insira o nome do ficheiro .s com os cruzamentos pelos quais o autocarro vai passar: ");
                    // "Por favor, remova a ultima linha em branco do(s) ficheiro(s) de texto";
                    String fileName = in.next();
                    System.out.println("Insira o identificador do cruzamento inicial: ");
                    initialId = in.nextInt();
                    while (initialId < 1 || initialId > adjList.length){
                        System.out.println("Identificador incorrecto.\nInsira o identificador do cruzamento inicial: ");
                        initialId = in.nextInt();
                    }

                    schoolPath(fileName);
                    break;
                case 'e':
                    end = true;
                    break;
                default:
                    System.out.println("Tecla inválida");
            }
        }
    }



    private static void readGraph(Scanner scan) throws IOException {
        while(!scan.next().equals("p"))
            scan.nextLine();
        scan.next();
        int crosses = scan.nextInt();
        int streets = scan.nextInt();
        adjList = new Node[crosses];
        int idx,weight,vertexIdx;
        while(scan.hasNextLine()){
            if(scan.next().equals("a")){
                idx = scan.nextInt();
                vertexIdx = scan.nextInt();
                weight = scan.nextInt();
                if(adjList[idx-1]==null) adjList[idx-1] = new Node(weight,vertexIdx,idx);
                else {
                    Node node = adjList[idx-1];
                    while(node.next!=null)
                        node = node.next;
                    node.next = new Node(weight,vertexIdx,idx);
                }
            }
            else if(scan.hasNext()) scan.nextLine();
        }
    }

    private static void schoolPath(String fileName) {
        try {
            Scanner scan2 = new Scanner(new FileReader(fileName));
            readSchoolPath(scan2);
            if (checkOddConnections() || isIsland())
                System.out.println("PERIGO! Não é possivel formar um circuito, que passe pelas escolas indicadas.");
            else {
                setCircuit();
                printCircuit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readSchoolPath(Scanner scan) throws IOException{
        int size = scan.nextInt();
        int[] schoolsVertex = new int [size];
        for(int i = 0; i< schoolsVertex.length; ++i)
            schoolsVertex[i] = scan.nextInt();
        setSubGraph(schoolsVertex);
    }

    private static void setSubGraph(int[] schoolsVertex) {
        int size = schoolsVertex.length;
        schools = new Node[size];
        for(int i=0;i<size;++i)
            findAdjacents(i,schoolsVertex);
    }

    private static void findAdjacents(int i, int[] schoolsVertex) {
        int vertex = schoolsVertex[i];
        Node aux = adjList[vertex-1];
        while(aux!=null){
            if(vertexInSubGraph(aux.adjVertex,schoolsVertex)){
                if(schools[i]==null)
                    schools[i] = new Node(aux.weight,newIdx+1,vertex);
                else schools[i].next = new Node(aux.weight,newIdx+1,vertex);
            }
            aux = aux.next;
        }
    }

    private static boolean vertexInSubGraph(int adjVertex, int[] schoolsVertex) {
        for(int i=0;i<schoolsVertex.length;++i)
            if(schoolsVertex[i]==adjVertex) {
                newIdx = i;
                return true;
            }
        return false;
    }

    private static boolean checkOddConnections() {
        int count;
        Node n;
        for (int i=0; i<schools.length; ++i) {
            count = 0;
            n = schools[i];
            while(n!=null){
                ++count;
                n = n.next;
            }
            if(count%2!=0) return true;
        }
        return false;
    }

    public static boolean isIsland() {
        visited = new boolean[schools.length];
        searchC(0);
        for(int i=0;i<visited.length;++i)
            if(!visited[i]){
                return true;
            }
        return false;
    }

    private static void searchC(int i) {
        visited[i] = true;
        Node node = schools[i];
        while(node!=null){
            if(!visited[node.adjVertex-1])
                searchC(node.adjVertex-1);
            node = node.next;
        }
    }

    private static void setCircuit() {
        s = new GenericStackList<>();
        int initial = getInitialIdx();
        s.push(initial);
        createEulerCycle();
    }

    private static void createEulerCycle() {
        boolean allVisited = true;
        int idx = (int)s.pop();
        Node node = schools[idx];
        while (node!= null) {
            if (!node.visited) {
                s.push(idx);
                node.visited = true;
                allVisited = false;
                break;
            }
            node = node.next;
        }
        if(allVisited) {
            s.push(idx);
            return;
        }
        Node inverseNode = schools[node.adjVertex-1];
        while((inverseNode.adjVertex-1)!=idx)
            inverseNode = inverseNode.next;
        inverseNode.visited = true;
        s.push(node.adjVertex-1);
        createEulerCycle();
    }

    private static void printCircuit() {
        int previous,actual=-1;
        if (!s.isEmpty()) actual = (int)s.pop();
        while (!s.isEmpty()){
            previous = actual;
            actual = (int)s.pop();
            Node w = schools[actual];
            while((w.adjVertex-1)!=previous)
                w=w.next;
            System.out.println(schools[previous].vertex+"->"+schools[actual].vertex+"("+w.weight+")");
        }
    }

    public static int getInitialIdx() {
        for(int i=0;i<schools.length;++i)
            if(schools[i].vertex==initialId)
                return i;
        return -1;
    }
}
