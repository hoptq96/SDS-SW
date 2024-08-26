import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS_LeaveOfficeTerm {
    static int r, c, mr, mc, min;
    static char[][] map;
    static boolean[][] check;
    static int[] movex = {1, -1, 0, 0};
    static int[] movey = {0, 0, 1, -1};
    static ArrayList<Node> key;

    static class Node {
        public Node(int mr, int mc, int i) {
            this.x = mr;
            this.y = mc;
            this.step = i;
        }

        int x;
        int y;
        int step;
    }

    static Queue<Node> queue;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/BFSSToE.txt"));
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        for (int zzz = 1; zzz <= testcase; zzz++) {
            r = sc.nextInt();
            c = sc.nextInt();
            mr = sc.nextInt() - 1;
            mc = sc.nextInt() - 1;
            sc.nextLine();
            map = new char[r][c];
            check = new boolean[r][c];
            min = Integer.MAX_VALUE;
            for (int i = 0; i < r; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < c; j++) {
                    map[i][j] = line.charAt(j);
                }
            }
            key = new ArrayList<>();
            BFSkey();

            if (key.isEmpty()) {
                if (min == Integer.MAX_VALUE) {
                    System.out.println("#" + zzz + " -1");
                } else {
                    System.out.println("#" + zzz + " " + min);
                }
            } else {
                for (int i = 0; i < key.size(); i++) {
                    reset();
                    BFSout(key.get(i));
                }
                System.out.println("#" + zzz + " " + min);
            }
        }
    }

    private static void BFSout(BFS_LeaveOfficeTerm.Node node) {
        int nextx, nexty;
        Node vitri = new Node(node.x, node.y, node.step);
        queue = new LinkedList<>();
        queue.add(vitri);
        while (!queue.isEmpty()) {
            vitri = queue.poll();
            check[vitri.x][vitri.y] = true;
            for (int i = 0; i < 4; i++) {
                nextx = vitri.x + movex[i];
                nexty = vitri.y + movey[i];
                if (checkrow(nextx) && checkcol(nexty) && check[nextx][nexty] == false) {
                    if (map[nextx][nexty] == 'O') {
                        check[nextx][nexty] = true;
                        if (min > vitri.step + 1) {
                            min = vitri.step + 1;
                        }
                    }
                    if (map[nextx][nexty] != 'X') {
                        Node next = new Node(nextx, nexty, vitri.step + 1);
                        check[nextx][nexty] = true;
                        queue.add(next);
//System.out.println("x: "+nextx+"y: "+nexty+"step: "+vitri.step + 1);
                    }
                }
            }
        }
//System.out.println("End queue");
        int v = Integer.MAX_VALUE;
        if (min == v) {
            min = -1;
        }
    }

    private static void reset() {
        check = new boolean[r][c];
    }

    private static void BFSkey() {
        int nextx, nexty;
        Node vitri = new Node(mr, mc, 0);
        queue = new LinkedList<>();
        queue.add(vitri);
        while (!queue.isEmpty()) {
            vitri = queue.poll();
            check[vitri.x][vitri.y] = true;
            for (int i = 0; i < 4; i++) {
                nextx = vitri.x + movex[i];
                nexty = vitri.y + movey[i];
                if (checkrow(nextx) && checkcol(nexty) && check[nextx][nexty] == false) {
                    if (map[nextx][nexty] == '_') {
                        check[nextx][nexty] = true;
                        Node next = new Node(nextx, nexty, vitri.step + 1);
                        queue.add(next);
                    }
                    if (map[nextx][nexty] == 'a') {
                        check[nextx][nexty] = true;
                        Node next = new Node(nextx, nexty, vitri.step + 1);
                        key.add(next);
                        queue.add(next);
                    }
                    if (map[nextx][nexty] == 'O') {
                        if (min > vitri.step + 1) {
                            min = vitri.step + 1;
                        }
                    }
                }
            }
        }
    }

    private static boolean checkrow(int nextx) {
        return 0 < nextx && nextx < r;
    }

    private static boolean checkcol(int nexty) {
        return 0 < nexty && nexty < c;
    }
}
