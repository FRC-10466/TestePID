package frc.robot.subsystem.torret;



import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystem.TestePID;

public class Controller {
    CommandXboxController xbox1 = new CommandXboxController(0); 
    CommandXboxController xbox2 = new CommandXboxController(1);

    private final TestePID testePID;
    private final Torret torret;

    public Controller(TestePID testePID, Torret torret){
        this.testePID = testePID;
        this.torret = torret;
    }

    public void periodic(){
        controlPid();
        controlTorret();
    }

    private void controlTorret() {
        double torreta = MathUtil.applyDeadband(xbox1.getLeftX(), 0.2) * 0.05;
        double shooter = MathUtil.applyDeadband(xbox1.getRightTriggerAxis(), 0.1);
        torret.control(torreta, shooter);
    }

    
    public void controlPid() {
    if (xbox2.getHID().getAButton()) {
        testePID.Alvo(TestePID.Laranja);
    } else if (xbox2.getHID().getBButton()) {
        testePID.Alvo(TestePID.Amarelo);
    } else if (xbox2.getHID().getXButton()) {
        testePID.Alvo(TestePID.Verde);
    } else if (xbox2.getHID().getYButton()) {
        testePID.Alvo(TestePID.Vermelho);
    } else if (xbox2.getHID().getLeftBumperButton()) {
        testePID.Alvo(TestePID.AzulEscuro);
    } else if (xbox2.getHID().getRightBumperButton()) {
        testePID.Alvo(TestePID.AzulClaro);
    } else {
        testePID.parar(); 
    }
}

}
