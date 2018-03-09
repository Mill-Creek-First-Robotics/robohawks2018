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
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Lift extends Subsystem  {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public double speed = .5f;
	private SpeedController m_motor;
	public Robot robot;

	public void initDefaultCommand() {
		m_motor = new Talon(Robot.m_robotMap.liftMotor);
		
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void up() {
		m_motor.set(speed);
	}
	
	public void down() {
		m_motor.set(-1 * speed);
	}
	
	public void stop() {
		m_motor.stopMotor();
	}
	
	public Boolean isMoving() {
		if (m_motor.get() <= 0.0f) { return true; }
		else { return false; }
	}

}
