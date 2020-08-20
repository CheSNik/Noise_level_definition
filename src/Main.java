import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Double.parseDouble;
import static java.lang.Math.log10;
import static java.lang.Math.pow;


/**
 * Assignment 4 P5.18
 * @author Sergei Chekhonin
 * This program defines noise level
 */

public class Main {
    /**
     * reflects level of noise
     */
    private static String noiseLevel = "";
    private static final double Po = 20*pow(10,-6);
    public static void main(String[] args) {
        PrintWriter out = null;
        try {
            out = new PrintWriter("Assignment4_P5.18output.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String input = JOptionPane.showInputDialog("Enter unit (dB or Pa):");
        /**
         * stores input unit
         */
        String unitType = input;

        input = JOptionPane.showInputDialog("Enter value:");
        /**
         * stores input value
         */
        double valueOfNoise = parseDouble(input);


        noiseLevel = computeNoise(unitType, valueOfNoise);

        out.println(dtf.format(now));
        out.printf("Measured noise value is: %.2f", valueOfNoise);
        out.printf(unitType + "\n");
        out.println("Level of noiise is defined as: "+ noiseLevel);

        out.close();
    }

    /***
     *
     * @param unitType input unit type
     * @param valueOfNoise input noise value
     * @return definition of noise type
     */
    public static String computeNoise(String unitType, double valueOfNoise)
    {
        String noiseDefinition="";

        double computedValueOfNoise;
        if  (unitType == "dB")
            computedValueOfNoise = valueOfNoise;
        else
            computedValueOfNoise = 20*log10(valueOfNoise/Po);

        if (computedValueOfNoise>= 130)
        noiseDefinition = "Threshold of pain";
        else if (computedValueOfNoise>= 120 && computedValueOfNoise <130)
            noiseDefinition = "Possible hearing damage";
        else if (computedValueOfNoise>= 100 && computedValueOfNoise <120)
            noiseDefinition = "Jack hummer at 1 m";
        else if (computedValueOfNoise>= 90 && computedValueOfNoise <100)
            noiseDefinition = "Traffic on a busy roadway at 10 m";
        else if (computedValueOfNoise>=60 && computedValueOfNoise <90)
            noiseDefinition = "Normal conversation";
        else if (computedValueOfNoise>= 30 && computedValueOfNoise <60)
            noiseDefinition = "Calm library";
        else
            noiseDefinition = "Light leaf rustling";

        return noiseDefinition;

    }
}
