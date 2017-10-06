package maze_solver;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.hardware.sensor.SensorMode;

public class Infrared 
{
	static  EV3LargeRegulatedMotor  LEFT_MOTOR  =  new  EV3LargeRegulatedMotor(MotorPort.C); 
    static  EV3LargeRegulatedMotor  RIGHT_MOTOR  =  new  EV3LargeRegulatedMotor(MotorPort.B);
    static EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S1);
    
    public static void main(String[] args) throws Exception
    {          
    		Button.waitForAnyPress();
		Wheel wheel1 = WheeledChassis.modelWheel(LEFT_MOTOR , 2.0).offset(-7.5);
		Wheel wheel2 = WheeledChassis.modelWheel(RIGHT_MOTOR , 2.0).offset(7.5);
		Chassis chassis = new WheeledChassis(new  Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);		      
		MovePilot pilot = new MovePilot(chassis);
		pilot.forward();	             
		Button.waitForAnyPress();
	    SensorMode irDistance = irSensor.getDistanceMode();
	    float [] samplevalue =  new float [irDistance.sampleSize()];
	    while(Button.getButtons() != Button.ID_ESCAPE)
	    {
	    		irDistance.fetchSample(samplevalue, 0);
	        System.out.println("value"+ samplevalue[0]);
	        if(samplevalue[0]>0)
			{
    	   			pilot.rotate(90);
    	   			pilot.forward();
			}
	        Thread.sleep(100);
	    }
    }
}
