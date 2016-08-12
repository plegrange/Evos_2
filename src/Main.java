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
        displayPatterns(allPatterns);
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
