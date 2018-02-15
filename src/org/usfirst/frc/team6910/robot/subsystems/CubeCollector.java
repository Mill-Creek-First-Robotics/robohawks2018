/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6910.robot.subsystems;

import org.usfirst.frc.team6910.robot.Robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem to manage the cube collector
 */
public class CubeCollector extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public double speed = .05f;
	private SpeedController m_motor;
	public Robot robot;
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new loop());
		m_motor = new Talon(Robot.m_robotMap.liftMotor);
	}
	
	public void push() {
		m_motor.set(speed);
	}
	
	public void pull() {
		m_motor.set(-1 * speed);
	}
	
	public void stop() {
		m_motor.stopMotor();
	}
}
