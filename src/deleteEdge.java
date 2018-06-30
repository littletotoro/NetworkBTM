import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class deleteEdge {

    public Map<String,String> deleteEdge(int wordsize,Map<String,String> edgeMap) throws IOException {
        /*
        Map<String,Integer> limitedEdge = new HashMap<>();
        Map<String,Integer> badEdge = new HashMap<>(); //被删除的边
        for (int i = 0 ; i < wordsize - 2; i++){
            System.out.println("开始第 " + i + " 个词的搜寻");
            for (int j = i+1 ; j < wordsize - 1 ; j++){
                for (int k = j+1 ; k < wordsize ; k++){
                    String edge_ij = i + "," + j;
                    String edge_ik = i + "," + k;
                    String edge_jk = j + "," + k;
                    if (edgeMap.containsKey(edge_ij) && edgeMap.containsKey(edge_ik) && edgeMap.containsKey(edge_jk)){
                        limitedEdge.put(edge_ij,edgeMap.get(edge_ij));
                        limitedEdge.put(edge_ik,edgeMap.get(edge_ik));
                        limitedEdge.put(edge_jk,edgeMap.get(edge_jk));

                    }
                }
            }
        }
        */

        Map<String,String> limitedEdge = new HashMap<>();
        Map<String,String> badEdge = new HashMap<>(); //被删除的边
        for (Map.Entry<String, String> entry : edgeMap.entrySet()) {
            System.out.println("开始边"+entry.getKey() + "的检索");
            for (int i = 0 ; i < wordsize ; i++){
                String[] wordID = entry.getKey().split(",");
                int id1 = Integer.parseInt(wordID[0]);
                int id2 = Integer.parseInt(wordID[1]);
                if (i != id1 && i != id2){
                    String edge1 = id1 > i ? i + "," + id1 : id1 + "," + i;
                    String edge2 = id2 > i ? i + "," + id2 : id2 + "," + i;
                    if (edgeMap.containsKey(edge1) && edgeMap.containsKey(edge2)){
                        String lineID1 = edgeMap.get(edge1);
                        String lineID2 = edgeMap.get(edge2);
                        String lineID3 = entry.getValue();
                        if (!lineID1.equals(lineID2) || !lineID1.equals(lineID3) || !lineID2.equals(lineID3)) {
                            limitedEdge.put(entry.getKey(), entry.getValue());
                            limitedEdge.put(edge1, edgeMap.get(edge1));
                            limitedEdge.put(edge2, edgeMap.get(edge2));
                        }

                    }
                }
            }
        }

        for (Map.Entry<String, String> entry : edgeMap.entrySet()) {
            if (!limitedEdge.containsKey(entry.getKey())) badEdge.put(entry.getKey(),entry.getValue());
        }
        System.out.println("被删除的边有 " + badEdge.size() + " 条。");
        mainFrame.outputEdgesWithString("bad_edges.txt",badEdge);

        return limitedEdge;
    }



}
