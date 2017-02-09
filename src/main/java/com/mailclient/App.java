package com.mailclient;

import com.mailclient.utils.PBConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author kunal
 * App class for postBox App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/postBox_parent.fxml"));
        primaryStage.setTitle(PBConstants.APP_TITLE);
        primaryStage.setScene(new Scene(root, PBConstants.APP_WIDTH, PBConstants.APP_HEIGHT));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
