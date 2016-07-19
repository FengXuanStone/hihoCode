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

    public static ArrayList<myPoint> find(char[][] maps, char[][] neighbor)  {
        char[] mid = neighbor[1];
        ArrayList<myPoint> pointArr = new ArrayList<myPoint>();

        for(int j = 0; j < maps[0].length - 2; j++) {
            for(int i = 1; i < maps.length - 1; i++) {
                if(maps[i][j] == mid[0] &&
                        maps[i][j + 1] == mid[1] &&
                        maps[i][j + 2] == mid[2]) {
                    if(maps[i - 1][j] == neighbor[0][0] &&
                            maps[i - 1][j + 1] == neighbor[0][1] &&
                            maps[i - 1][j + 2] == neighbor[0][2]) {
                        if(maps[i + 1][j] == neighbor[2][0] &&
                                maps[i + 1][j + 1] == neighbor[2][1] &&
                                maps[i + 1][j + 2] == neighbor[2][2]) {
                            myPoint tmp = new myPoint(i + 1, j + 2);
                            pointArr.add(tmp);
                        }
                    }
                } else if(maps[i][j] == mid[2] &&
                        maps[i][j + 1] == mid[1] &&
                        maps[i][j + 2] == mid[0]) {
                    if(maps[i - 1][j] == neighbor[2][2] &&
                            maps[i - 1][j + 1] == neighbor[2][1] &&
                            maps[i - 1][j + 2] == neighbor[2][0]) {
                        if(maps[i + 1][j] == neighbor[0][2] &&
                                maps[i + 1][j + 1] == neighbor[0][1] &&
                                maps[i + 1][j + 2] == neighbor[0][0]) {
                            myPoint tmp = new myPoint(i + 1, j + 2);
                            pointArr.add(tmp);
                        }
                    }
                }
            }
        }

        return pointArr;
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
