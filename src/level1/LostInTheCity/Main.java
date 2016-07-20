package level1.LostInTheCity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by fengxuan on 2016/7/19.
 */
public class Main {
    public static class myPoint{
        int x = -1; int y = -1;

        myPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static char[][] xuanzhuan(char a[][],int N){
        char[][] b = new char[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                b[N-1-j][N-1-i] = a[i][N-1-j];
            }
        }
        return b;
    }

    public static void rotate(char a[][],int N) {
        for (int layer = 0; layer < N / 2; layer++) {
            int first = layer;
            int last = N - 1 - layer;
            for (int i = layer; i < last; i++) {
                int offset = i - layer;
                char top = a[first][i];
                a[first][i] = a[last - offset][first];
                a[last - offset][first] = a[last][last - offset];
                a[last][last - offset] = a[i][last];
                a[i][last] = top;
            }
        }
    }

    public static boolean match(char[][] maps, char[][] neighbor, int i, int j) {
        for(int x = 0; x < neighbor.length; x++) {
            for(int y = 0; y < neighbor[0].length; y++) {
                if(maps[i + x][j + y] != neighbor[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static ArrayList<myPoint> find(char[][] maps, char[][] neighbor)  {
        ArrayList<myPoint> result = new ArrayList<>();

        for(int i = 0; i < maps.length - neighbor.length + 1; i++) {
            for(int j = 0; j < maps[0].length - neighbor[0].length + 1; j++) {
                boolean b1 = match(maps, neighbor, i , j);
                rotate(neighbor, neighbor.length);
                boolean b2 = match(maps, neighbor, i , j);
                rotate(neighbor, neighbor.length);
                boolean b3 = match(maps, neighbor, i , j);
                rotate(neighbor, neighbor.length);
                boolean b4 = match(maps, neighbor, i , j);
                rotate(neighbor, neighbor.length);
                if(b1 || b2 || b3 || b4) {
                    result.add(new myPoint(i + neighbor.length / 2 + 1,
                            j + neighbor.length / 2 + 1));
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s1 = br.readLine().split(" ");
        int N = Integer.parseInt(s1[0]);
        int M = Integer.parseInt(s1[1]);

        char[][] maps = new char[N][M];
        for(int i = 0; i < N; i++) {
            String ss = br.readLine();
            for(int j = 0; j < M; j++) {
                maps[i][j] = ss.charAt(j);
            }
        }

        char[][] neighbor = new char[3][3];
        for(int i = 0; i < 3; i++) {
            String ss = br.readLine();
            for(int j = 0; j < 3; j++) {
                neighbor[i][j] = ss.charAt(j);
            }
        }

        ArrayList<myPoint> pointArr = find(maps, neighbor);

        for(int i = 0; i < pointArr.size(); i++) {
            System.out.println(pointArr.get(i).x + " " + pointArr.get(i).y);
        }

    }
}
