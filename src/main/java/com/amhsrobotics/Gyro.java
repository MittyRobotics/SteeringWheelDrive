package com.amhsrobotics;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Gyro extends ADXRS450_Gyro {
    private static Gyro Instance = new Gyro();
    public static Gyro getInstance(){
        return Instance;
    }
    private Gyro(){
        super();
    }
}
