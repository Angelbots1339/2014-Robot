package org.usfirst.frc.team1339.robot.commands;

import org.usfirst.frc.team1339.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 */
public class DriveWithJoystick extends CommandBase {
	
	Joystick stick;
	double throttle, turn;
	boolean GTA = false;
	double GTASpeed = 0;
	int toggleRunTime = 10;
	int cooldown = toggleRunTime;
	double GTATurn;

    public DriveWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(PIDChassis);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	stick = oi.getJoytstick();
    	
    	cooldown++;
    	
    	if(oi.getArcadePOV() == 180 && oi.getR3() == true && cooldown >= toggleRunTime){
    		cooldown = 0;
    		GTA = !GTA;
    		
    	}
    	
    	
    	//System.out.println(stick.getRawAxis(RobotMap.rightTrigger));
    	
    	throttle = stick.getRawAxis(RobotMap.leftYAxis);
    	turn = stick.getRawAxis(RobotMap.rightXAxis);
    	GTATurn = stick.getRawAxis(RobotMap.rightXAxis);
    	
    	if(stick.getRawAxis(RobotMap.rightTrigger) > 0.8){
    		GTASpeed = (stick.getRawAxis(RobotMap.leftYAxis)+1)/2;
    	}
    	else if(stick.getRawAxis(RobotMap.leftTrigger) > 0.8) {
    		GTASpeed = (stick.getRawAxis(RobotMap.leftYAxis)-1)/2;
    	}
    	else{
    		GTASpeed = 0;
    	}
    	//if 
 
    	//Dead Zone
    	if(throttle <= 0.05 && throttle >= -0.05){
    		throttle = 0;
    	}
    	if(turn <= 0.05 && turn >= -0.05){
    		turn = 0;
    	}
    	
    	//Expontional Gain
    	if(GTA == false){
    		throttle = throttle * throttle * throttle;   
        	turn = turn * turn * turn;
        	//throttle *= 0.5;
        	PIDChassis.arcadeDrive(throttle, turn);
    	}
    	if(GTA == true){
    		PIDChassis.arcadeDrive(GTASpeed, GTATurn);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
