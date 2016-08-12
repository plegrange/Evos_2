import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FuBaR on 8/12/2016.
 */
public class Main {

    public static void main(String[] args) {
        new Main();
    }

    String inputFile = "C://Users//FuBaR//IdeaProjects//Evos_2/HourlyData.xls";
    List<Pattern> allPatterns;

    public Main() {
        try {
            read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        runNeuralNet();
        //displayPatterns(allPatterns);
    }

    private void normalizePatterns(){

    }

    NeuralNet neuralNet;

    private void runNeuralNet() {
        neuralNet = new NeuralNet(6, 1, 2);
        for (int i = 0; i < 30; i++)
            trainNeuralNet();
        testNeuralNet();
    }

    private void trainNeuralNet() {
        for (int i = 0; i < 3000; i++) {
            Pattern pattern = allPatterns.get(i);
            for (int j = 0; j < 24; j++) {
                double[] data = new double[6];
                data[0] = pattern.year;
                data[1] = pattern.month;
                data[2] = pattern.dayOfMonth;
                data[3] = pattern.dayOfWeek;
                data[4] = j;
                data[5] = -1;
                neuralNet.trainNeuralNetwork(data, pattern.hourlyData[j]);
            }
        }
    }

    private void testNeuralNet() {
        for (int i = 3000; i < 3300; i++) {
            Pattern pattern = allPatterns.get(i);
            for (int j = 0; j < 24; j++) {
                double[] data = new double[6];
                data[0] = pattern.year;
                data[1] = pattern.month;
                data[2] = pattern.dayOfMonth;
                data[3] = pattern.dayOfWeek;
                data[4] = j;
                data[5] = -1;
                double output = neuralNet.getOutput(data);
                displayPrediction(output, pattern.hourlyData[j]);
            }
        }
    }

    private void displayPrediction(double prediction, double target) {
        System.out.println(target + " -> " + prediction);
    }

    private void read() throws IOException {
        allPatterns = new ArrayList<>();
        File inputWorkBook = new File(inputFile);
        Workbook workbook;
        try {
            workbook = Workbook.getWorkbook(inputWorkBook);
            Sheet sheet = workbook.getSheet(0);
            for (int y = 1; y < 3373; y++) {
                double[] data = new double[28];
                for (int x = 0; x < 28; x++) {
                    data[x] = Double.valueOf(sheet.getCell(x, y).getContents());

                }
                allPatterns.add(new Pattern(data));
            }
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

    private void displayPatterns(List<Pattern> patterns) {
        for (Pattern pattern : patterns) {
            pattern.display();
        }
    }
}
