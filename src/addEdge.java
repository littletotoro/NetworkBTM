import java.util.HashMap;
import java.util.Map;

public class addEdge {
    public Map<String,Integer> add(int wordsize, Map<String,Integer> edgeMap){
        Map<String,Integer> extendEdge = new HashMap<>();
        double[][] S = new double[wordsize][wordsize]; //权重矩阵
        double[] D = new double[wordsize]; //节点的度矩阵的逆
        double[][] A = new double[wordsize][wordsize]; //节点概率转移矩阵

        //构建权重矩阵
        for (Map.Entry<String, Integer> entry : edgeMap.entrySet()) {
            extendEdge.put(entry.getKey(),entry.getValue());
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            String[] node = entry.getKey().split(",");
            S[Integer.parseInt(node[0])][Integer.parseInt(node[1])] = entry.getValue();
            S[Integer.parseInt(node[1])][Integer.parseInt(node[0])] = entry.getValue();
        }

        //构建度对角矩阵的逆
        for (int i = 0 ; i < wordsize ; i++){
            int degree = 0;
            for (int j = 0 ; j < wordsize ; j++){
                degree += S[i][j];
            }
            D[i] = (double)1/degree;
        }

        //构建概率转移矩阵
        for (int i = 0 ; i < wordsize ; i++){
            for (int j = 0 ; j < wordsize ; j++){
                A[i][j] = D[i] * S[i][j];
            }
        }

        //得到二阶近似矩阵
        double[][] secondStep = calMatrix(A,A);

        //计算转移概率的阈值

        //非零概率的平均值
        int nonZeroCount = 0;
        double nonZeroSum = 0;
        for (int i = 0 ; i < secondStep.length ; i++){
            for (int j = 0 ; j < secondStep[0].length ; j++){
                if (secondStep[i][j] != 0){
                    nonZeroCount++;
                    nonZeroSum += secondStep[i][j];
                }
            }
        }

        double nonZeroAverage = nonZeroSum/(double)nonZeroCount;

        //含零概率的平均值
        double includeZeroSum = 0;
        for (int i = 0 ; i < secondStep.length ; i++){
            for (int j = 0 ; j < secondStep[0].length ; j++){
                includeZeroSum += secondStep[i][j];
            }
        }

        double includeZeroAverage = includeZeroSum/(double)(secondStep.length*secondStep[0].length);


        //筛选可能存在的边
        for (int i = 0 ; i < secondStep.length ; i++){
            for (int j = 0 ; j < secondStep[0].length ; j++){
                if (secondStep[i][j] >= nonZeroAverage){
                    String eEdge = i < j ? i + "," + j : j + "," + i;
                    if (!extendEdge.containsKey(eEdge)) extendEdge.put(eEdge,1);
                }
            }
        }

        System.out.println("增加的边有 " + (extendEdge.size() - edgeMap.size()) + " 条。");

        return extendEdge;
    }

    public double[][] calMatrix(double[][] A,double[][] B){ //矩阵乘法
        double[][] result = new double[A.length][B.length];
        for (int i = 0 ; i < A.length ; i++){
            for (int j = 0 ; j < B.length ; j++){
                for (int k = 0 ; k < A[0].length ; k++){
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }


    /*
    public static void main(String[] args){
        double[][] A = new double[2][2];
        double[][] B = new double[2][2];

        for (int i = 0 ; i < 2 ; i++){
            A[i][i] = i+1;
            for (int j = 0 ; j < 2 ; j++){
                B[i][j] = 2*i + (j+1);
            }
        }

        addEdge ae = new addEdge();

        double[][] C = ae.calMatrix(A,B);

        for (int i = 0 ; i < 2 ; i++){
            for (int j = 0 ; j < 2 ; j++){
                System.out.println(C[i][j]);
            }
        }

    }*/
}
