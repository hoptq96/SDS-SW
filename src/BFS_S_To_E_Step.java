import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class BFS_S_To_E_Step {
    //    Tính khoảng cách ngắn nhất và hiển thị các bước đi từ S -> E
    static int row, coll, startRow, startColl, endRow, endColl;

    static String[][] map;
    static final short MOD = 1000;
    static int[][] distance; // visited
    static Queue<Integer> queue;
    static int[][] direction = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static Scanner sc;
    //    luu buoc di tu S -> E
    static HashMap<Integer, Integer> listNode;
    static List listStep;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/BFSSToE.txt"));
        sc = new Scanner(System.in);
        short tc = sc.nextShort();
        for (int i = 0; i < tc; i++) {

            System.out.println("Case: " + i);
            row = sc.nextInt();
            coll = sc.nextInt();
            startRow = sc.nextInt();
            startColl = sc.nextInt();
            sc.nextLine();

            readInputFile();
            BFS();
            System.out.println("strRow= " + startRow + " | strColl= " + startColl);
            System.out.println("endRow= " + endRow + " | endColl= " + endColl);
            print();
        }
    }

    private static void readInputFile() throws FileNotFoundException {
        map = new String[row][coll];
        for (int i = 0; i < row; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < coll; j++) {
                map[i][j] = String.valueOf(line.charAt(j));
                System.out.print(line.charAt(j));
            }
            System.out.println();
        }
    }

    private static void BFS() {

        distance = new int[row][coll];
        listNode = new HashMap<>();
        queue = new LinkedList<>();
        queue.add(startRow * MOD + startColl);
        distance[startRow][startColl] = 1;

        while (!queue.isEmpty()) {
            int currentPosition = queue.poll();
            int x = currentPosition / MOD;
            int y = currentPosition % MOD;
            for (int i = 0; i < direction.length; i++) {
                int neighBorX = x + direction[i][0];
                int neighBorY = y + direction[i][1];

                if (neighBorX >= 0 && neighBorY >= 0 && neighBorX < row && neighBorY < coll && distance[neighBorX][neighBorY] == 0) {
                    if ("X".equals(map[neighBorX][neighBorY])) {
                        continue;
                    }

                    int neighBor = neighBorX * MOD + neighBorY;
                    queue.add(neighBor);
                    distance[neighBorX][neighBorY] = distance[x][y] + 1;
                    listNode.put(neighBor, currentPosition);

                    if ("E".equals(map[neighBorX][neighBorY])) {
                        endRow = neighBorX;
                        endColl = neighBorY;
                        break;
                    }
                }
            }
        }
    }

    static void print() {
        System.out.println("Khoang cach tu S - E la: " + distance[endRow][endColl]);
        listStep = new ArrayList();
        listStep.add(map[endRow][endColl]);
        int previousPosition = endRow * MOD + endColl;
        int fatherNode;
        for (int i = 1; i < listNode.size(); i++) {
            fatherNode = listNode.get(previousPosition);
            int x = fatherNode / MOD;
            int y = fatherNode % MOD;
            listStep.add(map[x][y]);
            previousPosition = fatherNode;
            if ("S".equals(listStep.get(i))) {
                break;
            }
        }
        Collections.reverse(listStep);
        System.out.println(listStep.toString());
    }
}