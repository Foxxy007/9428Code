package frc.robot;
import java.lang.Math;

public class Util {
    //Basic input curve that can vary in linearity. 1 is linear 0 is a cubic.
    //https://www.desmos.com/calculator/q5rq5vkpgk
    public static double inputCurve(double inputX, double linearity){//keep this Static to make sure that we can call it without creating an object(an instance of the class)
        double y;
        double a = 1-linearity;
        y = (linearity*inputX)+(a*Math.pow(inputX, 3));
        return y;
    }
    //Keeps values between -1 and 1
    public static double clamp(double x){
        double clampedValue;
        clampedValue = Math.max(-1, Math.min(1, x));
        return clampedValue;
    }
}