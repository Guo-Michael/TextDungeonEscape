package Main.observer;

import java.util.Observable;
import java.util.Observer;

public class AnnoyingObserver implements Observer {

    private int roomsSwitchCounter = 0;

    // MODIFIES: roomsSwitchCounter
    // EFFECTS: Increments the roomsSwitchCounter by 1 each time the observer is updated/notified
    @Override
    public void update(Observable o, Object arg) {
        roomsSwitchCounter++;
    }

    public int getNumRoomsSwitched() {
        return roomsSwitchCounter;
    }

}
