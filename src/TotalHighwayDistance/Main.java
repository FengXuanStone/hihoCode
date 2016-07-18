package TotalHighwayDistance;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by fengxuan on 2016/7/17.
 */
public class Main {
    public static class EdgeNode{
        int beginNum = -1;
        int endNum = -1;
        int weight = 0;
        int subCount = 0;
        ArrayList<EdgeNode> subNodes = new ArrayList<EdgeNode>();
    }

    public static void Insert(EdgeNode e, int u, int v, int weight) {
        if(e.beginNum == -1 || e.endNum == -1) {
            e.beginNum = u;
            e.endNum = v;
            e.weight = weight;
            return;
        }

        if(e.beginNum == u || e.beginNum == v || e.endNum == v || e.endNum == u) {
            EdgeNode tmp = new EdgeNode();
            tmp.beginNum = u;
            tmp.endNum = v;
            tmp.weight = weight;
            e.subNodes.add(tmp);
        } else {
            for(int i = 0; i < e.subNodes.size(); i++) {
                Insert(e.subNodes.get(i), u, v, weight);
            }
        }
    }

    public static void dfs1(EdgeNode root) {
        dfs(root);
    }

    public static int dfs(EdgeNode root) {
        if(root == null) return 0;

        if(root.subNodes.isEmpty()) {
            root.subCount = 0;
            return 1;
        }

        int result = 0;
        for(int i = 0; i < root.subNodes.size(); i++) {
           result += dfs(root.subNodes.get(i));
        }
        root.subCount = result;
        return result + 1;
    }

    public static int count(EdgeNode root, int lineNum) {
        if(root == null) return 1;

        if(root.subNodes.isEmpty()) {
            return (root.subCount + 1) * (lineNum - root.subCount - 1) * root.weight;
        }

        int result = 0;
        for(int i = 0; i < root.subNodes.size(); i++) {
            result += count(root.subNodes.get(i), lineNum);
        }
        result += (root.subCount + 1) * (lineNum - root.subCount - 1) * root.weight;

        return result;
    }



    public static EdgeNode findNode(EdgeNode node, int u, int v) {
        if(node == null) return null;

        if(node.beginNum == u && node.endNum == v) {
            return node;
        } else if(node.beginNum == v && node.endNum == u){
            return node;
        } else {
            for(int i = 0; i < node.subNodes.size(); i++) {
                EdgeNode e = findNode(node.subNodes.get(i), u, v);
                if(e != null) {
                    return e;
                }
            }
        }
        return null;
    }

    public static int newCount(int count, EdgeNode root, int u, int v, int weight, int lineNum) {
        EdgeNode e = findNode(root, u, v);
        int result = count + (weight - e.weight) * (root.subCount + 1) * (lineNum - root.subCount - 1);
        e.weight = weight;
        return result;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");

        int lineNum = Integer.parseInt(firstLine[0]);
        int querys = Integer.parseInt(firstLine[1]);

        EdgeNode root = new EdgeNode();

        for(int i = 0; i < lineNum - 1; i++) {
            String[] t1 = br.readLine().split(" ");
            Insert(root, Integer.parseInt(t1[0]),Integer.parseInt(t1[1]),Integer.parseInt(t1[2]));
        }

        dfs1(root);

        int result = count(root, lineNum);

        for(int i = 0; i < querys; i++) {
            String[] sArr = br.readLine().split(" ");
            if(sArr[0].equals("QUERY")) {
                System.out.println(result);
            } else if(sArr[0].equals("EDIT")){
                result = newCount(result, root, Integer.parseInt(sArr[1]),Integer.parseInt(sArr[2]),
                        Integer.parseInt(sArr[3]), lineNum);
            }
        }
    }
}
