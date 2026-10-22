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
import com.revrobotics.RelativeEncoder;







public class TestePID {
    private final Ultrasonic Sensor = new Ultrasonic(0, 1);
    private final PIDController Pid = new PIDController(0.01, 0, 0);
    private final SparkMax Motor = new SparkMax(3, MotorType.kBrushless);
    private final RelativeEncoder encoder = Motor.getEncoder();
    private final MedianFilter filtro = new MedianFilter(5);
    private final SparkMaxConfig config = new SparkMaxConfig();
    
    private final double PotenciaMax = 0.10;
    private final double LimiteRotacoes = 0.0;

    private double distanciaCm = 0;
    private double ultimaLeitura = -1;
    
    public static final double Laranja = 2.25;
    public static final double Amarelo = 6.75;
    public static final double Verde = 11.25;
    public static final double Vermelho = 15.75;
    public static final double AzulEscuro = 20.25;
    public static final double AzulClaro = 24.75;

    public TestePID() {
    Ultrasonic.setAutomaticMode(true);

    config.smartCurrentLimit(15).idleMode(IdleMode.kBrake).inverted(false);
    config.softLimit.forwardSoftLimit(LimiteRotacoes).forwardSoftLimitEnabled(true);
    config.softLimit.reverseSoftLimit(-LimiteRotacoes).reverseSoftLimitEnabled(true);

        encoder.setPosition(0);
        SmartDashboard.putData("PID/controlador", Pid);
        Motor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    
    public void atualizarSensor() {
        double leitura = Sensor.getRangeMM() / 10.0;
        if (Sensor.isRangeValid() && leitura != ultimaLeitura) {
            distanciaCm = filtro.calculate(leitura); // só entra no filtro quando o sensor mediu de novo
            ultimaLeitura = leitura;
        }
        SmartDashboard.putBoolean("PID/sensor_valido", Sensor.isRangeValid());
        SmartDashboard.putNumber("PID/distancia_cm", distanciaCm);
        SmartDashboard.putNumber("PID/encoder_rot", encoder.getPosition());
    }
    
    
    public void irPara(double alvo) {
        if (!Sensor.isRangeValid()) {
            Motor.set(0);
            return;
        }
        
        double distanciaCm = filtro.calculate(Sensor.getRangeMM() / 10.0);
        double potencia = MathUtil.clamp(Pid.calculate(distanciaCm, alvo), -PotenciaMax, PotenciaMax);
        Motor.set(potencia);
        
        SmartDashboard.putNumber("PID/alvo_cm", alvo);
        SmartDashboard.putNumber("PID/potencia", potencia);
    }


    
    public void parar(){
        Pid.reset();
        filtro.reset();
        Motor.set(0);
        SmartDashboard.putNumber("PID/potencia", 0);
    }

}

    





