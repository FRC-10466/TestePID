package frc.robot;



import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystem.torret.Torret;
import frc.robot.subsystem.TestePID;
import frc.robot.subsystem.torret.Controller;

public class Robot extends TimedRobot {

  private final TestePID testePID = new TestePID();
  private final Torret  torret = new Torret();

  private final Controller controller = new Controller(testePID, torret);

  /** Called once at the beginning of the robot program. */
  public Robot() {
  }

  @Override
  public void robotPeriodic(){
    testePID.atualizarSensor();
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    controller.periodic();
    
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
