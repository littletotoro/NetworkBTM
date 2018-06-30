import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class mainFrame {

    public static void outputEdges(String filepath,Map<String,Integer> edges) throws IOException {
        File outputFile = new File(filepath);
        FileWriter fileWriter = new FileWriter(outputFile);

        for (Map.Entry<String, Integer> entry : edges.entrySet()){
            fileWriter.write(entry.getKey() + "\n");
        }
    }


    public static void outputEdgesWithString(String filepath,Map<String,String> edges) throws IOException {
        File outputFile = new File(filepath);
        FileWriter fileWriter = new FileWriter(outputFile);

        for (Map.Entry<String, String> entry : edges.entrySet()){
            fileWriter.write(entry.getKey() + "\n");
        }
    }

    public static void main(String[] args) throws IOException {
        String filepath = "content_data.txt";

        //将文本文件读入
        transLinesToNetwork tLN = new transLinesToNetwork();
        tLN.buildStopWordsMap();
        tLN.readCorpus(filepath);


        //删掉其中一些不紧密的边
        deleteEdge dE = new deleteEdge();
        Map<String,String> deCollection = dE.deleteEdge(tLN.wordMap.size(),tLN.edgeMapWithID);
        outputEdgesWithString("limit_data.txt",deCollection);

        /*
        //增加其中一些可能的边（通过二阶近似）
        addEdge aE = new addEdge();
        Map<String,Integer> aeCollection = aE.add(tLN.wordMap.size(),tLN.edgeMap);

        //以增加边的基础进行删边
        deleteEdge deleteAddEdge = new deleteEdge();
        Map<String,Integer> deAddCollection = deleteAddEdge.deleteEdge(tLN.wordMap.size(),aeCollection);

        //以删除边得基础进行加边
        addEdge addDeleteEdge = new addEdge();
        Map<String,Integer> addDelCollection = addDeleteEdge.add(tLN.wordMap.size(),deCollection);

        */
    }
}
