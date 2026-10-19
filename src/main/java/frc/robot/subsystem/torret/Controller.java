package frc.robot.subsystem.torret;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystem.TestePID;

public class Controller {
    TestePID testePID = new TestePID();
    
    public Controller() {

    }

    CommandXboxController xbox1 = new CommandXboxController(0); 
    CommandXboxController xbox2 = new CommandXboxController(1);

    double botaoShooter = xbox1.getRightTriggerAxis();
    boolean botaocorrigit = xbox1.a().getAsBoolean();

    double Velocidade_torreta() {
        return MathUtil.applyDeadband(xbox1.getLeftX(), 0.2);
    }

    double Velocidade_shooter() {
        return MathUtil.applyDeadband(xbox1.getRightTriggerAxis(), 0.1);
    }

    boolean ligado = botaocorrigit;
    boolean ultimo_botao = false;
    
    public void botaooo(SparkMax motor) {
        if(ligado && ultimo_botao) {
            ligado = !ligado;
            if(ligado = true) {
                motor.set(1);
            } else {
                motor.set(0);
            }

        } 
        ligado = ultimo_botao;
    }

    
    public void controlPid(){
        if(xbox2.getHID().getAButtonPressed()){
            testePID.setAlvo(TestePID.Laranja);
        }
        else if(xbox2.getHID().getBButtonPressed()){
            testePID.setAlvo(TestePID.Amarelo);
        }
        else if(xbox2.getHID().getXButtonPressed()){
            testePID.setAlvo(TestePID.Verde);
        }
        else if(xbox2.getHID().getYButtonPressed()){
            testePID.setAlvo(TestePID.Vermelho);
        }
        else if(xbox2.getHID().getLeftBumperPressed()){
            testePID.setAlvo(TestePID.AzulEscuro);
        }
        else if(xbox2.getHID().getRightBumperPressed()){
            testePID.setAlvo(TestePID.AzulClaro);
        }

        testePID.executar();
    }

}
