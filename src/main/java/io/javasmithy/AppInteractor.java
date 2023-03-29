package io.javasmithy;

import io.javasmithy.domain.Time;
import javafx.animation.AnimationTimer;

public class AppInteractor {

    private final long SECOND_INTERVAL = 1_000_000_000L;
    private TimeModel model;

    public AppInteractor(TimeModel model){
        this.model = model;
    }

    public void start(){
        AnimationTimer timer = new AnimationTimer() {
            long last = System.nanoTime();
            @Override
            public void handle(long l) {
                if (l >= (last+SECOND_INTERVAL)) {
                    last = l;
                    model.setDate(Time.getDate());
                    model.setTime(Time.getTime());
                }
            }
        };
        timer.start();
    }

}
