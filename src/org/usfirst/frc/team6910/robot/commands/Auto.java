/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6910.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team6910.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class Auto extends Command {
	private boolean hasTraveled = false;
	private double speed = -0.8f;
	public long length = 2000; // Time in milliseconds
	private long startTime, endTime;
	
	public Auto() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.m_tankDrive);
//		timer = new Timer();
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
//		timer.schedule(new StopTask(), length, 1000);
		Robot.m_tankDrive.m_DiffDrive.setExpiration(.5);
		startTime = System.currentTimeMillis();
		System.out.println(startTime);
		endTime = startTime + length;
		hasTraveled = false;
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.m_tankDrive.drive(speed, speed);
		if (System.currentTimeMillis() >= endTime) {
			Robot.m_tankDrive.drive(0.0f, 0.0f);
			hasTraveled = true;
			
		}
		
		

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return hasTraveled;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		hasTraveled = true;
//		timer.cancel();
		
	}
	
//	class StopTask extends TimerTask {
//		public void run() {
//			hasTraveled = true;
//			timer.cancel();
//		}
//	}
}
