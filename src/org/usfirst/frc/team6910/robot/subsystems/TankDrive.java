/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6910.robot.subsystems;

import org.usfirst.frc.team6910.robot.Robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class TankDrive extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public DifferentialDrive m_DiffDrive; // Differential Drive for controlling drive chain 
	private double m_MotorSensitivity = -.8f;
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		Spark m_leftFrontSpark = new Spark(Robot.m_robotMap.leftFrontMotor);
		Spark m_leftBackSpark = new Spark(Robot.m_robotMap.leftBackMotor);
		SpeedControllerGroup m_leftMotorGroup = new SpeedControllerGroup(m_leftFrontSpark, m_leftBackSpark);
		m_leftFrontSpark.setSafetyEnabled(false);
		m_leftBackSpark.setSafetyEnabled(false);
		
		Spark m_rightFrontSpark = new Spark(Robot.m_robotMap.rightFrontMotor);
		Spark m_rightBackSpark = new Spark(Robot.m_robotMap.rightBackMotor);
		SpeedControllerGroup m_rightMotorGroup = new SpeedControllerGroup(m_rightFrontSpark, m_rightBackSpark);
		m_rightFrontSpark.setSafetyEnabled(false);
		m_rightBackSpark.setSafetyEnabled(false);
		
		m_DiffDrive = new DifferentialDrive(m_leftMotorGroup, m_rightMotorGroup);
		
		m_DiffDrive.setSafetyEnabled(false);
		
	}
	
	public void drive(double left, double right) {
		m_DiffDrive.tankDrive( left * m_MotorSensitivity, right * m_MotorSensitivity);
	}
	
	public void set_sens(double s) {
		m_MotorSensitivity = s;
	}
	public double get_sens(double s) {
		return s;
	}
}
