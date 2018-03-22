package org.usfirst.frc.team6910.robot.commands.auto;

import org.usfirst.frc.team6910.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterAutoSequence extends CommandGroup {
	public long centerLength = 500;
	private char position = 'c';
	private char target;
	boolean safeToPlace = true;
	
	public CenterAutoSequence() {
		centerLength = Robot.m_prefs.getLong("Center Length", 500);
		position = Robot.m_prefs.getString("Position", "c").charAt(0);

		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		
		if (gameData.length() > 0){
			target = gameData.charAt(0);
		} else {
			safeToPlace = false;k
		}

		
		addSequential(new StraightAuto(1000));
		addSequential(new TurnAuto(false));
		addSequential(new StraightAuto(1000));
		if (safeToPlace) addSequential(new PlaceCubeAuto());
	}
}
