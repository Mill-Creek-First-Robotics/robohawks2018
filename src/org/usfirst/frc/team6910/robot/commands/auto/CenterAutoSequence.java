package org.usfirst.frc.team6910.robot.commands.auto;

import org.usfirst.frc.team6910.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterAutoSequence extends CommandGroup {
	public long centerLength = 500;
	private char position = 'c';
	private char target;
	private long[] timings;
	boolean safeToPlace = true;
	
	public CenterAutoSequence() {
		centerLength = Robot.m_prefs.getLong("Center Length", 500);
		position = Robot.m_prefs.getString("Position", "c").charAt(0);
		timings =  Robot.m_prefs.getLong("centerAutoTiming", 0);
		
		Robot.m_ds.reportWarning(timings.toString(), false);

		String gameData = Robot.m_ds.getGameSpecificMessage();
		
		
		if (gameData.length() > 0){
			target = gameData.charAt(0);
		} else {
			safeToPlace = false;
		}

		
		addSequential(new StraightAuto(timings[0]));
		addSequential(new TurnAuto(false));
		addSequential(new StraightAuto(timings[1]));
		addSequential(new TurnAuto(true));
		addSequential(new StraightAuto(timings[3]));
		if (safeToPlace) addSequential(new PlaceCubeAuto());
	}
}
