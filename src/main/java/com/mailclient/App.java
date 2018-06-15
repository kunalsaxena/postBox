package com.mailclient;

import com.mailclient.utils.InitUtil;
import com.mailclient.utils.PBConstants;
import com.mailclient.utils.PBUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** @author kunal App class for postBox App */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        if (null == PBUtils.readConfigObject()) {
            Stage login = FXMLLoader.load(getClass().getResource("/login.fxml"));
            login.setTitle("postbox login");
            login.centerOnScreen();
            login.show();
            login.setAlwaysOnTop(true);
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/postBox_parent.fxml"));
            primaryStage.setTitle(PBConstants.APP_TITLE);
            primaryStage.setScene(new Scene(root, PBConstants.APP_WIDTH, PBConstants.APP_HEIGHT));
            primaryStage.show();
        }

        /*
         * Stage init = FXMLLoader.load(getClass().getResource("/init.fxml"));
         * init.setTitle("postbox email client"); init.centerOnScreen(); init.show();
         * init.setAlwaysOnTop(true);
         */

        // init.close();
    }

    public static void main(String[] args) {

        InitUtil initUtil = new InitUtil();
        initUtil.init();

        launch(args);
    }
}
