/**
 * Created by FuBaR on 8/12/2016.
 */
public class Pattern {
    double year, month, dayOfMonth, dayOfWeek;
    double[] hourlyData;

    public Pattern(double[] data) {
        year = data[0];
        month = data[1];
        dayOfMonth = data[2];
        dayOfWeek = data[3];
        hourlyData = new double[24];
        for (int i = 0; i < 24; i++) {
            hourlyData[i] = data[i + 4];
        }
    }

    public void display() {
        System.out.print(year + " " + month + " " + dayOfMonth + " " + dayOfWeek + " ");
        for (int i = 0; i < 24; i++) {
            System.out.print(hourlyData[i] + " ");
        }
        System.out.println();
    }
}
