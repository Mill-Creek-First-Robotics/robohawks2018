package org.usfirst.frc.team6910.robot.commands.auto;

import org.usfirst.frc.team6910.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class FancyAuto extends CommandGroup {
	public long centerLength = 500;
	private char position = 'c';
	private char target;
	boolean safeToPlace = true;
	private long length;
	private boolean direction = false;
	
	public FancyAuto() {
		centerLength = Robot.m_prefs.getLong("Center Length", 500);
		position = Robot.m_prefs.getString("Position", "c").charAt(0);
		long[] timings =  {Robot.m_prefs.getLong("centerAutoTimingA", 500), Robot.m_prefs.getLong("centerAutoTimingB", 750), Robot.m_prefs.getLong("centerAutoTimingC", 250)};
		length = Robot.m_prefs.getLong("SidesAutoLength", 2000);
		
		DriverStation.reportWarning(timings.toString(), false);

		String gameData = Robot.m_ds.getGameSpecificMessage();
		
		if (gameData.length() > 0){
			target = gameData.charAt(0);
		} else {
			safeToPlace = false;
		}
		
		switch(target) {
			case 'L': direction = true; break;
			case 'R': direction = false; break;
			default: direction = false; break;
		}
		
		if (position == 'c') {

			addSequential(new StraightAuto(timings[0]));
			addSequential(new TurnAuto(false));
			addSequential(new StraightAuto(timings[1]));
			addSequential(new TurnAuto(direction));
			addSequential(new StraightAuto(timings[2]));
			
		} else {
			if (position == 'l' || position == 'r') {
				if (position == Character.toLowerCase(target)) {
					safeToPlace = true;
				}
				addSequential(new StraightAuto(length));
				addSequential(new TurnAuto(!direction)); 
				addSequential(new StraightAuto(timings[2]));
			}
		}
		if (safeToPlace) addSequential(new PlaceCubeAuto());
	}
}
