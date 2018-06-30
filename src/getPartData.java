import java.io.*;

public class getPartData {
    public static void main(String[] args) throws IOException {
        File inputfile = new File("toutiao_cat_data.txt");
        File outputContentFile = new File("content_data.txt");
        FileWriter contentWriter = new FileWriter(outputContentFile);
        File outputLabelFile = new File("label_data.txt");
        FileWriter labelWriter = new FileWriter(outputLabelFile);

        if (inputfile.exists()&&inputfile.isFile()){
            InputStreamReader inputReader = new InputStreamReader(new FileInputStream(inputfile));
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            String lineTxt = null;
            while((lineTxt = bufferedReader.readLine()) != null){
                double ran = Math.random();
                if (ran <= 0.025){
                    String[] tmp = lineTxt.split("_!_");
                    contentWriter.write(tmp[3] + "\t" + tmp[1] + "\n");
                    labelWriter.write(tmp[1] + "\n");
                }

            }
            bufferedReader.close();
            inputReader.close();
        }
        labelWriter.close();
        contentWriter.close();


    }
}
