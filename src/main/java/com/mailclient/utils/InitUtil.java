package com.mailclient.utils;

import com.google.gson.JsonObject;
import com.mailclient.dto.ConfigDTO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/** Created by kunal on 21/2/17. */
public class InitUtil {

  // Create folders and files at application start
  public void init() {

    File folder = new File(PBConstants.USER_HOME + "/postbox/inbox");
    if (!folder.exists()) {
      folder.mkdirs();
    }

    // Create inbox file
    File file = new File(folder, "inbox");
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // Create inbox file
    File propFile = new File(folder, "../mailProperties");

    try {
      if (!propFile.exists()) {
        propFile.createNewFile();
        PBUtils.initConfig();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    // Call MailCheck
    // CheckMail.check();

  }
}
