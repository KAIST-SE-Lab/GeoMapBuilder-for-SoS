package kr.ac.kaist.se.model.intf;

import kr.ac.kaist.se.data.SimLogEvent;

import java.util.ArrayList;

/**
 * Interface for simulatable objects.
 * @author ymbaek
 */
public interface Simulatable {

    /**
     * A method to run its own behaviors
     * @param tick current tick of simulation
     * @return a list of SimLogEvents generated from executing the behaviors
     */
    ArrayList<SimLogEvent> run(int tick);
}
