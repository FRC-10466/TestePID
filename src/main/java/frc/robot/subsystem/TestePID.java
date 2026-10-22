package frc.robot.subsystem;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.math.controller.PIDController;



import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;




public class TestePID {
    private final Ultrasonic Sensor = new Ultrasonic(0, 1);
    private final PIDController Pid = new PIDController(0.01, 0, 0);
    private final SparkMax Motor = new SparkMax(1, MotorType.kBrushless);
    

    public TestePID() {
    Ultrasonic.setAutomaticMode(true);}

    private double pontoInicial = 13.5;

    public static final double Laranja = 2.25;
    public static final double Amarelo = 6.75;
    public static final double Verde = 11.25;
    public static final double Vermelho = 15.75;
    public static final double AzulEscuro = 20.25;
    public static final double AzulClaro = 24.75;

    public void setAlvo(double novaDistancia){
        this.pontoInicial = novaDistancia;
    }

    public void executar(){
        double distanciaAtual = Sensor.getRangeMM() * 10.0;
        double potencia = Pid.calculate(distanciaAtual, pontoInicial);
        Motor.set(potencia);
    }

    




}
