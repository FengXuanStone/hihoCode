package microsoft.april2016;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by fengxuan on 2016/7/16.
 */
public class demoday {
    public static class mark{
        int right;
        int down;
        char node;
    }

    public static int minSearch(mark[][] table, int row, int col) {
        //初始点
        table[0][0].right = 0;
        table[0][0].down = table[0][1].node == 'b' ? 0 : 1;
        //1 行
        for(int i = 1; i < col; i++) {
            //right
            if(table[0][i].node == 'b') table[0][i].right = table[0][i - 1].right + 1;
            else table[0][i].right = table[0][i - 1].right;
            //down
            if(i == col - 1) {
                table[0][i].down = table[0][i].right;
            } else {
                if(table[0][i + 1].node == 'b') {
                    table[0][i].down = table[0][i].right;
                } else {
                    table[0][i].down = table[0][i].right + 1;
                }
            }
        }
        //1 列
        for(int i = 1; i < row; i++) {
            //down
            if(table[i][0].node == 'b') table[i][0].down = table[i - 1][0].down + 1;
            else table[i][0].down = table[i - 1][0].down;
            //right
            if(i == row - 1) {
                table[i][0].right = table[i - 1][0].down;
            } else {
                if(table[0][i + 1].node == 'b') {
                    table[i][0].right = table[i - 1][0].down;
                } else {
                    table[i][0].right = table[i - 1][0].down + 1;
                }
            }
        }

        for(int i = 1; i < row; i++) {
            for(int j = 1; j < col; j++) {
                int right1 = 0, right2 = 0, down1 = 0, down2 = 0;
                //从上边下来
                if(table[i][j].node == 'b') {
                    down1 = table[i - 1][j].down + 1;
                } else {
                    down1 = table[i - 1][j].down;
                }
                if(i == row - 1) {
                    right1 = down1;
                } else {
                    if(table[i + 1][j].node == 'b') {
                        right1 = down1;
                    } else {
                        right1 = down1 + 1;
                    }
                }
                //从左边来
                if(table[i][j].node == 'b') {
                    right2 = table[i][j - 1].right + 1;
                } else {
                    right2 = table[i][j - 1].right;
                }
                if(j == col - 1) {
                    down2 = right2;
                } else {
                    if(table[i][j + 1].node == 'b'){
                        down2 = right2;
                    } else {
                        down2 = right2 + 1;
                    }
                }
                //update
                table[i][j].down = Math.min(down1, down2);
                table[i][j].right = Math.min(right1, right2);
            }
        }

        return Math.min(table[row - 1][ col - 1].down, table[row - 1][col - 1].right);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] msg = br.readLine().split(" ");
        int row = Integer.parseInt(msg[0]);
        int col = Integer.parseInt(msg[1]);
        mark[][] table = new mark[row][col];
        for (int i = 0; i < row; i++) {
            char[] tmp = br.readLine().toCharArray();
            for(int j = 0; j < tmp.length; j++) {
                mark m = new mark();
                m.node = tmp[j];
                table[i][j] = m;
            }
        }
        int result = minSearch(table, row, col);
       System.out.println(result);


//        int row = 4;
//        int col = 8;
//        String[] ss = {"....bb..", "........",  ".....b..",   "...bb..."};
//        mark[][] table = new mark[row][col];
//        for (int i = 0; i < row; i++) {
//            char[] tmp = ss[i].toCharArray();
//            for(int j = 0; j < tmp.length; j++) {
//                mark m = new mark();
//                m.node = tmp[j];
//                table[i][j] = m;
//            }
//        }
//
//
//        int result = minSearch(table, row, col);
//        System.out.println(result);
    }
}
