package frc.robot;
import java.lang.Math;

public class Util {
    //Basic input curve that can vary in linearity. 
    //1 is linear 0 is a cubic.
    public double inputCurve(double inputX, double linearity){
        double y;
        double a = 1-linearity;
        y = (linearity*inputX)+(a*Math.pow(inputX, 3));
        return y;
    }
}
