package io.javasmithy;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.util.Builder;
import org.kordamp.ikonli.codicons.Codicons;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.function.Consumer;

public class AppViewBuilder implements Builder<Region> {
    private double xOffset;
    private double yOffset;
    private TimeModel model;
    private BorderPane root;

    public  AppViewBuilder(TimeModel model){
        this.model = model;
        this.root = new BorderPane();
    }

    @Override
    public Region build(){
        this.root.getStylesheets().add(
                getClass().getResource("css/root.css").toExternalForm()
        );
        setRootNodeDraggable();
        Node top = createTop();
        BorderPane.setAlignment(top, Pos.CENTER);
        BorderPane.setMargin(top, new Insets(8));
        System.out.println(BorderPane.getAlignment(top).toString());
        this.root.setTop(top);

        Node center = createCenter();
        BorderPane.setAlignment(center, Pos.CENTER);
        BorderPane.setMargin(center, new Insets(8));
        this.root.setCenter(center);
        return root;
    }

    private Node createCenter(){
        VBox vBox = new VBox();
        Label timeLabel = new Label();
        timeLabel.textProperty().bindBidirectional(model.timeProperty());
        Label dateLabel = new Label();
        dateLabel.setId("date");
        dateLabel.textProperty().bindBidirectional(model.dateProperty());
        vBox.getChildren().addAll(timeLabel, dateLabel);
        return vBox;
    }

    private Node createTop(){
        HBox hBox = new HBox();
        hBox.getChildren().addAll(
                refreshCSSButton("css/root.css"),
                exitButton()
        );
        return hBox;
    }

    private Button exitButton(){
        FontIcon icon = new FontIcon(Codicons.CLOSE);
        icon.setId("icon");
        Button button = new Button("", icon);
        button.setOnAction( evt -> {
            Platform.exit();
        });
        return button;
    }

    private Button refreshCSSButton(String path){
        FontIcon icon = new FontIcon(Codicons.REFRESH);
        icon.setId("icon");
        Button button = new Button("", icon);
        button.setOnAction( evt -> {
            this.root.getStylesheets().set(0, getClass().getResource(path).toExternalForm());
            System.out.println("[INFO] Changed Stylesheets");
        });
        return button;
    }

    private Button circleMenuButton(){
        FontIcon icon = new FontIcon(Codicons.CIRCLE_OUTLINE);
        icon.setId("icon");
        Button button = new Button("", icon);
        return button;
    }

    private void setRootNodeDraggable(){
        this.root.setOnMousePressed( evt -> {
            this.xOffset = evt.getSceneX();
            this.yOffset = evt.getSceneY();
        });

        this.root.setOnMouseDragged( evt -> {
            this.root.getScene().getWindow().setX(evt.getScreenX() - this.xOffset);
            this.root.getScene().getWindow().setY(evt.getScreenY() - this.yOffset);
        });
    }
}

