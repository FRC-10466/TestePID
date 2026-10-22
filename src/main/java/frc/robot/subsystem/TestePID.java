package frc.robot.subsystem;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.MedianFilter;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;







public class TestePID {
    private final Ultrasonic Sensor = new Ultrasonic(0, 1);
    private final PIDController Pid = new PIDController(0.01, 0, 0);
    private final SparkMax Motor = new SparkMax(3, MotorType.kBrushless);
    private final double PotenciaMax = 0.10;
    private final MedianFilter filtro = new MedianFilter(5);
    private final SparkMaxConfig config = new SparkMaxConfig();

    
    public static final double Laranja = 2.25;
    public static final double Amarelo = 6.75;
    public static final double Verde = 11.25;
    public static final double Vermelho = 15.75;
    public static final double AzulEscuro = 20.25;
    public static final double AzulClaro = 24.75;

    public TestePID() {
    Ultrasonic.setAutomaticMode(true);

    config.smartCurrentLimit(15).idleMode(IdleMode.kBrake).inverted(false);
        Motor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
    

    public void irPara(double alvo) {
    if (!Sensor.isRangeValid()) {
        Motor.set(0);
        return;
    }

    double distanciaCm = filtro.calculate(Sensor.getRangeMM() / 10.0);
    double potencia = MathUtil.clamp(Pid.calculate(distanciaCm, alvo), -PotenciaMax, PotenciaMax);
    Motor.set(potencia);

    SmartDashboard.putNumber("PID/distancia_cm", distanciaCm);
        SmartDashboard.putNumber("PID/alvo_cm", alvo);
        SmartDashboard.putNumber("PID/potencia", potencia);
}

    
    public void parar(){
        Pid.reset();
        filtro.reset();
        Motor.set(0);
    }

}

    





