package com.mailclient.postbox;

import java.io.IOException;

import com.mailclient.dto.ConfigDTO;
import com.mailclient.utils.JWTTokenUtil;
import com.mailclient.utils.PBUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/** Created by kunal on 17/3/17. */
public class LoginController {

  @FXML private TextField username;

  @FXML private PasswordField password;

  @FXML
  private void handleLoginBtn(ActionEvent event) throws IOException {

    // Write username and password to config object
    ConfigDTO configDTO = PBUtils.readConfigObject();

    // Generate token
    String token = JWTTokenUtil.createToken(username.getText(), password.getText());

    configDTO.setToken(token);

    // update config obj
    PBUtils.writeConfig(configDTO);
  }
}
