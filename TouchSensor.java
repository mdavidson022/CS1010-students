package maze_solver;
import  lejos.robotics.navigation.*;
import  lejos.hardware.motor.EV3LargeRegulatedMotor; 
import  lejos.hardware.port.MotorPort; 
import  lejos.robotics.chassis.Chassis; 
import  lejos.robotics.chassis.Wheel; 
import  lejos.robotics.chassis.WheeledChassis;
import lejos.hardware.Button;
import lejos.hardware.sensor.EV3TouchSensor;
import  lejos.hardware.sensor.SensorMode;
import lejos.hardware.port.SensorPort;

public class TouchSensor 
{
	static  EV3LargeRegulatedMotor  LEFT_MOTOR  =  new  EV3LargeRegulatedMotor(MotorPort.C); 
    static  EV3LargeRegulatedMotor  RIGHT_MOTOR  =  new  EV3LargeRegulatedMotor(MotorPort.B);
    static EV3TouchSensor touchSensor = new EV3TouchSensor(SensorPort.S1);
    
    public static void main(String[] args) throws Exception
    {          
    		Button.waitForAnyPress();
		Wheel wheel1 = WheeledChassis.modelWheel(LEFT_MOTOR , 2.0).offset(-7.5);
		Wheel wheel2 = WheeledChassis.modelWheel(RIGHT_MOTOR , 2.0).offset(7.5);
		Chassis chassis = new WheeledChassis(new  Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);		      
		MovePilot pilot = new MovePilot(chassis);
		pilot.forward();	             
		Button.waitForAnyPress();
		SensorMode toucher = touchSensor.getTouchMode();
		float [] samplevalue =  new float [toucher.sampleSize()]; 
		while(Button.getButtons() != Button.ID_ESCAPE)
		{                       
			toucher.fetchSample(samplevalue, 0);
			System.out.println("value"+ samplevalue[0]);
			if(samplevalue[0]==1)
			{
    	   			pilot.rotate(90);
    	   			pilot.forward();
			}
			Thread.sleep(100);
		}
    	}
}

