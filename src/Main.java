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
        normalizePatterns();
        runNeuralNet();
        //displayPatterns(allPatterns);
    }

    double[] upperBound = new double[28], lowerBound = new double[28];

    private void normalizePatterns() {
        int buffer = 3;
        for (int i = 0; i < 28; i++) {
            upperBound[i] = -9999;
            lowerBound[i] = 9999;
        }
        for (Pattern pattern : allPatterns) {
            for (int i = 0; i < 4; i++) {
                if (pattern.pattern[i] < lowerBound[i])
                    lowerBound[i] = pattern.pattern[i];
                if (pattern.pattern[i] * buffer > upperBound[i])
                    upperBound[i] = pattern.pattern[i] * buffer;
            }
            for (int i = 4; i < 28; i++) {
                if (pattern.hourlyData[i - 4] < lowerBound[i])
                    lowerBound[i] = pattern.hourlyData[i - 4];
                if (pattern.hourlyData[i - 4] * buffer > upperBound[i])
                    upperBound[i] = pattern.hourlyData[i - 4] * buffer;
            }
        }
        for (Pattern pattern : allPatterns) {
            for (int i = 0; i < 4; i++) {
                pattern.pattern[i] = (pattern.pattern[i] - lowerBound[i]) / (upperBound[i] - lowerBound[i]);
            }
            for (int i = 4; i < 28; i++) {
                pattern.hourlyData[i - 4] = (pattern.hourlyData[i - 4] - lowerBound[i]) / (upperBound[i] - lowerBound[i]);
            }
        }
    }

    NeuralNet neuralNet;

    private void runNeuralNet() {
        //double meanError = 999;
        int iterations = 1000;
        neuralNet = new NeuralNet(6, 1, 10);
        for (int i = 0; i < iterations; i++) {
            trainNeuralNet();
            //if(i%(iterations/100)==0)
            String s = String.format("%.1f", Double.valueOf(i) / Double.valueOf(iterations)*100);
            System.out.println(s + "%");
        }
        testNeuralNet();
    }

    private void trainNeuralNet() {
        for (int i = 0; i < 3300; i++) {
            Pattern pattern = allPatterns.get(i);
            for (int j = 0; j < 24; j++) {
                double[] data = new double[6];
                data[0] = pattern.pattern[0];
                data[1] = pattern.pattern[1];
                data[2] = pattern.pattern[2];
                data[3] = pattern.pattern[3];
                data[4] = j;
                data[5] = -1;
                neuralNet.trainNeuralNetwork(data, pattern.hourlyData[j]);
            }
        }
    }

    private double denormalize(double value, int index) {
        return value * (upperBound[index] - lowerBound[index]) + lowerBound[index];
    }

    private double testNeuralNet() {
        double SSE = 0;
        int counter = 0;
        for (int i = 3300; i < 3372; i++) {
            Pattern pattern = allPatterns.get(i);
            for (int j = 0; j < 24; j++) {
                double[] data = new double[6];
                data[0] = pattern.pattern[0];
                data[1] = pattern.pattern[1];
                data[2] = pattern.pattern[2];
                data[3] = pattern.pattern[3];
                data[4] = j;
                data[5] = -1;
                double output = neuralNet.getOutput(data);
                // displayPrediction(output, pattern.hourlyData[j]);
                double SE = displayPrediction(denormalize(output, j + 4), denormalize(pattern.hourlyData[j], j + 4));
                counter++;
                SSE += SE;
            }
            System.out.println();
            System.out.println();
        }
        double meanError = Math.sqrt(SSE / counter);
        System.out.println("Mean error: " + meanError);
        return meanError;
    }

    private double displayPrediction(double prediction, double target) {
        System.out.print(target + "->" + prediction + " | ");
        return Math.pow(target - prediction, 2);
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
