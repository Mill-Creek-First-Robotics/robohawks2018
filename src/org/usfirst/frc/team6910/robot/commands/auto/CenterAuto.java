/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6910.robot.commands.auto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team6910.robot.Robot;

/**
 * Flexible Auto Mode
 */
public class CenterAuto extends Command {
	private boolean hasTraveled = false;
	private double speed = -0.8f;
	private int stage = 0;
	private long startTime, endTime;
	
	private long turnLength = 250;
	public long[] length = {1000, 1000, 1000}; // Times in milliseconds
	public long centerLength = 500;
	
	private boolean isTurning = false;
	private boolean hasDoneFirstTurn = false;
	
	private char position = '0';
	private char target = '0';
	private boolean safeToPlace = true;
	
	public CenterAuto() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.m_tankDrive);
		
//		timer = new Timer();
		

	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		centerLength = Robot.m_prefs.getLong("Center Length", 500);
		position = Robot.m_prefs.getString("Position", "c").charAt(0);

		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		if (gameData.length() > 0){
			target = gameData.charAt(0);
		} else {
			safeToPlace = false;
		}

		if (position == 'c') length[0] = 500;

		Robot.m_tankDrive.m_DiffDrive.setExpiration(.5);
		startTime = System.currentTimeMillis();
		System.out.println(startTime);
		endTime = startTime + length[0];
		hasTraveled = false;


	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (!isTurning) {
			Robot.m_tankDrive.drive(speed, speed);
			if (System.currentTimeMillis() >= endTime) {
				Robot.m_tankDrive.drive(0.0f, 0.0f);
				startTime = System.currentTimeMillis();
				endTime = startTime + turnLength;
				if (stage >= length.length) {
					Robot.m_tankDrive.drive(0.0f, 0.0f);
					hasTraveled = true;
				}
			}
		} else {
			if (!hasDoneFirstTurn) {
				Robot.m_tankDrive.drive(-speed, speed);
				if (System.currentTimeMillis() >= endTime) hasDoneFirstTurn = true;
			} else {
				Robot.m_tankDrive.drive(speed, -speed);
			}
			if (System.currentTimeMillis() >= endTime) {
				Robot.m_tankDrive.drive(0.0f, 0.0f);
				startTime = System.currentTimeMillis();
				stage += 1;
				endTime = startTime + length[stage];
				isTurning = false;
			}
			
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
