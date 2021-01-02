package funny.co.core;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyboardHandler implements EventHandler<KeyEvent> {
    private static final Logger log = Logger.getLogger(KeyboardHandler.class.getName());
    private final Caller caller;

    public KeyboardHandler(Caller caller) {
        this.caller = caller;
    }

    @Override
    public void handle(KeyEvent e) {
        log.log(Level.FINE, "{0} key pressed", e.getCode());
        switch (e.getCode()) {
            case ESCAPE:
                log.info("exiting application");
                caller.close();
        }
    }
}
