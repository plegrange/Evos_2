/**
 * Created by FuBaR on 8/12/2016.
 */
public class Pattern {
    double[] pattern;
    double[] hourlyData;

    public Pattern(double[] data) {
        pattern = new double[4];
        for (int i = 0; i < 4; i++)
            pattern[i] = data[i];
        hourlyData = new double[24];
        for (int i = 0; i < 24; i++) {
            hourlyData[i] = data[i + 4];
        }
    }

    public void display() {
        System.out.print(pattern[0] + " " + pattern[1] + " " + pattern[2] + " " + pattern[3] + " ");
        for (int i = 0; i < 24; i++) {
            System.out.print(hourlyData[i] + " ");
        }
        System.out.println();
    }
}
