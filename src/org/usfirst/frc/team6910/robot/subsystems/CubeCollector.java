/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6910.robot.subsystems;

import org.usfirst.frc.team6910.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem to manage the cube collector
 */
public class CubeCollector extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public double speed = .6f;
	private SpeedController m_motorA;
	private SpeedController m_motorB;
	private SpeedControllerGroup m_motor;
	public Robot robot;
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new loop());
		m_motorA = new Talon(6);
		m_motorB = new Talon(7);
		m_motor = new SpeedControllerGroup(m_motorA, m_motorB);
	
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
