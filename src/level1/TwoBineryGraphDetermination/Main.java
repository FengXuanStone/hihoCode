package level1.TwoBineryGraphDetermination;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by fengxuan on 2016/7/20.
 */
public class Main {
    public static class vertex{
        int id;
        int color = 0;
        ArrayList<vertex> neighbor = new ArrayList<>();

        vertex(int id) {
            this.id = id;
        }
    }

    public static boolean isColorGraph(vertex[] verArr) {
        if(verArr.length <= 1) return true;

        for(int k = 0; k < verArr.length; k++) {
            vertex ver = verArr[k];

            if(ver.color == 0) {
                ArrayList<vertex> candidate = new ArrayList<>();
                candidate.add(ver);
                int color = 1;

                while (!candidate.isEmpty()) {
                    ArrayList<vertex> tmp = new ArrayList<>();
                    for(int i = 0; i < candidate.size(); i++) {
                        candidate.get(i).color = color;

                        ArrayList<vertex> neighbor = candidate.get(i).neighbor;

                        for(int j = 0; j < neighbor.size(); j++) {
                            if(neighbor.get(j).color == 0) {
                                tmp.add(neighbor.get(j));
                            } else if(neighbor.get(j).color == candidate.get(i).color) {
                                return false;
                            }
                        }
                    }
                    color = color == 1 ? 2 : 1;
                    candidate.clear();
                    candidate.addAll(tmp);
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s1 = Integer.parseInt(br.readLine());

        while (s1 > 0) {
            String[] str = br.readLine().split(" ");
            int vertexNum = Integer.parseInt(str[0]);
            int edgeNum = Integer.parseInt(str[1]);

            vertex[] vertices = new vertex[vertexNum];
            //init
            for(int i = 0; i < vertexNum; i++) {
                vertices[i] = new vertex(i + 1);
            }

            while (edgeNum > 0) {
                String[] edgeMag = br.readLine().split(" ");
                int src = Integer.parseInt(edgeMag[0]);
                int dst = Integer.parseInt(edgeMag[1]);
                //update neighbor
                vertices[src - 1].neighbor.add(vertices[dst - 1]);
                vertices[dst - 1].neighbor.add(vertices[src - 1]);

                edgeNum--;
            }

            boolean iscolor = isColorGraph(vertices);

            if(iscolor) {
                System.out.println("Correct");
            } else {
                System.out.println("Wrong");
            }

            s1--;
        }
    }
}
