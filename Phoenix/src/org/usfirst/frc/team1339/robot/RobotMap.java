package org.usfirst.frc.team1339.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	// motor ports 
	
	public static int leftMotorOnePort = 1;
	public static int leftMotorTwoPort = 2;
	public static int leftMotorThreePort = 3;
	public static int rightMotorOnePort = 4;
	public static int rightMotorTwoPort = 5;
	public static int rightMotorThreePort = 6;
	
	// Joystick
	public static int joystickPort = 0;
	public static int leftYAxis = 1; //1
	public static int rightYAxis = 3; //5
	public static int rightXAxis = 2; //2
	public static int rightTrigger = 3;
	public static int leftTrigger = 2;
	
	public static int upSolenoidPort = 0;
	public static int downSolenoidPort = 1;

	
}
