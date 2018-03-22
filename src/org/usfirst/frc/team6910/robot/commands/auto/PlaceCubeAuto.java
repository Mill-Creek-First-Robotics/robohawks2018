/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6910.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6910.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */



public class PlaceCubeAuto extends Command {
	private boolean hasTraveled = false;
	public long length = 500; // Time in milliseconds
	private long startTime, endTime;
	public PlaceCubeAuto() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.m_cc);
		
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		startTime = System.currentTimeMillis();
		System.out.println(startTime);
		endTime = startTime + length;
		hasTraveled = false;
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.m_cc.push();

		
		if (System.currentTimeMillis() >= endTime) {
			Robot.m_cc.stop();
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
	}
}
