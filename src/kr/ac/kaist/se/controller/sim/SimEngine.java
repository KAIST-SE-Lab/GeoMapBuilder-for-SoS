package kr.ac.kaist.se.controller.sim;

import kr.ac.kaist.se.model.sos.SoS;

import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Controller class to perform a simulation.
 * Since this class is independent from SIMVA-SoS Lite,
 * This SimEngine only supports basic functions to execute a simulation model.
 *
 * @author ymbaek
 */
public class SimEngine {

    /** Logging */
    private static final String logFormat = "[%1$tF %1$tT] [%2$-7s] %3$s %n";
    private final Logger logger = Logger.getLogger("Simulation Engine Logger");
    private FileHandler fileHandler;

    /** Time information */
    private int simTick = 0;            //current simulation tick
    private int simTotalTime = 10;      //total time for simulation (i.e., end-condition)

    /** Simulation model */
    private SoS simModel;

    public SimEngine(SoS simModel) {
        this.simModel = simModel;

        initLogger();
    }


    public void startSimulation(){

        logger.info("(pre-simulation) A SimEngine object is constructed and initialized.");
        logger.info("(pre-simulation) ─────────────────────────────────────────────────────");

        for (;simTick < simTotalTime; simTick++){
            System.out.println(simTick);
            logger.info("(simulation) A SimEngine object is constructed and initialized.");
        }
    }


    /**
     * Method for logging of simulation engine
     */
    private void initLogger(){
        System.setProperty("java.util.logging.SimpleFormatter.format", logFormat);

        try {
            fileHandler = new FileHandler("SimEngineLog.log");
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);

            fileHandler.setFormatter(new SimpleFormatter() {
                @Override
                public synchronized String format(LogRecord lr) {
                    return String.format(logFormat,
                            new Date(lr.getMillis()),
                            lr.getLevel().getLocalizedName(),
                            lr.getMessage()
                    );
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
