package org.usfirst.frc.team1339.robot.subsystems;

import org.usfirst.frc.team1339.robot.RobotMap;
import org.usfirst.frc.team1339.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class PIDChassis extends PIDSubsystem {
	private static final double Kp = 0.0;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;
    
    private CANTalon motorRightOne, motorRightTwo, motorRightThree;
    private CANTalon motorLeftOne, motorLeftTwo, motorLeftThree;
    private Solenoid shiftUpSolenoid, shiftDownSolenoid;
    

    // Initialize your subsystem here
    public PIDChassis() {
    	super("PIDChassis", Kp, Ki, Kd);
    	
    	motorRightOne = new CANTalon(RobotMap.rightMotorOnePort);
    	motorRightTwo = new CANTalon(RobotMap.rightMotorTwoPort);
    	motorRightThree = new CANTalon(RobotMap.rightMotorThreePort);
    	motorLeftOne = new CANTalon(RobotMap.leftMotorOnePort);
    	motorLeftTwo = new CANTalon(RobotMap.leftMotorTwoPort);
    	motorLeftThree = new CANTalon(RobotMap.leftMotorThreePort);
    	
    	shiftUpSolenoid = new Solenoid(RobotMap.upSolenoidPort);
    	shiftDownSolenoid = new Solenoid(RobotMap.downSolenoidPort);
    	
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoystick());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void arcadeDrive(double throttle, double turn){
    	double left = throttle;
    	double right = throttle;
    	
    	
    	double turningThrottleScale = 0.75;
        
        // If there's no throttle, we still want to be able to turn
        if (java.lang.Math.abs(throttle) > 0.1) {
            turningThrottleScale = java.lang.Math.abs(throttle);
            
        }
        // Check the turning deadband, if we're past the deadband apply the turn
        // Otherwise we ignore the turn joystick
        if (turn > 0.04 || turn < -0.04) {
            // scale the turning based on the throttle
            left -= turn * turningThrottleScale;  
            right += turn * turningThrottleScale; 
        }
    	
    	/*
    	turn = turn/2;
    	throttle = throttle*throttle*throttle;
    	if(throttle > 0){
    		left = -throttle + turn;
    		right = throttle - turn;
    	}
    	else if(throttle < -0){
    		left = -throttle - turn;
    		right = throttle + turn;
    	}
    	else{
    		left = 0+turn;
    		right = 0+turn;		
    	}
    		*/
    	setLeftRight(left, right);
    }
    
    private void setLeftRight(double left, double right){
    	left *= -1;
    	motorRightOne.set(right);
    	motorRightTwo.set(right);
    	motorRightThree.set(right);
    	motorLeftOne.set(left);
    	motorLeftTwo.set(left);
    	motorLeftThree.set(left);
    }
    
    public void shiftUp(){
    	shiftUpSolenoid.set(true);
    	shiftDownSolenoid.set(false);
    	Timer.delay(.2);
    	shiftUpSolenoid.set(false);
    }
    
    public void shiftDown(){
    	shiftDownSolenoid.set(true);
    	shiftUpSolenoid.set(false);
    	Timer.delay(.2);
    	shiftDownSolenoid.set(false);
    	
    }
    
    public void driveForward(double speed){
    	setLeftRight(speed, speed);
    }
    
    public void turnLeft(double speed){
    	setLeftRight(-speed, speed);
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return 0.0;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
}
