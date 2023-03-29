package io.javasmithy;


import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class AppController {
    private Builder<Region> viewBuilder;
    private AppInteractor interactor;

    public AppController(){
        TimeModel model = new TimeModel();
        this.interactor = new AppInteractor(model);
        this.interactor.start();
        this.viewBuilder = new AppViewBuilder(model);
    }

    public Region getView(){
        return this.viewBuilder.build();
    }

}
