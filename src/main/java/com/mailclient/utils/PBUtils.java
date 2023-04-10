package com.mailclient.utils;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailclient.dto.ConfigDTO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/** Created by kunal on 1/2/17. */
public class PBUtils {

  public static String generateEmailHash(String from, Long time) {
    StringBuilder sb = new StringBuilder();
    sb.append(from);
    sb.append(time);
    while (sb.indexOf(" ") > 0) {
      sb.deleteCharAt(sb.indexOf(" "));
    }
    return sb.toString();
  }

  public static ConfigDTO readConfigObject() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    try {
      Path path = Paths.get(PBConstants.USER_HOME + "/postbox/mailProperties");
      if(Files.exists(path) && Files.isRegularFile(path)) {
        ConfigDTO configDTO =
            mapper.readValue(path.toFile(), ConfigDTO.class);
        return configDTO;
      } else {
        return null;
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      initConfig();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void writeConfig(ConfigDTO configDTO) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(new File(PBConstants.USER_HOME + "/postbox/mailProperties"), configDTO);
  }

  public static void initConfig() throws IOException {
    ConfigDTO configDTO = new ConfigDTO();
    configDTO.setGmailLastSyncedEmailNo(0l);
    configDTO.setToken("");
    writeConfig(configDTO);
  }

  public static String readToken() throws IOException {
    ConfigDTO configDTO = PBUtils.readConfigObject();
    if (null != configDTO.getToken()) return configDTO.getToken();
    else return null;
  }

  /*public static void changeConfigObject(String key, int value) {

      // Create inbox file
      String userHome = System.getProperty("user.home");
      File folder = new File(userHome + "/postbox/inbox");
      File propFile = new File(folder, "../mailProperties");

      try {
          if (!propFile.exists()) {
              propFile.createNewFile();
          }
          FileWriter fw = new FileWriter(propFile);
          JsonObject obj = new JsonObject();
          obj.addProperty(key, value);
          fw.write(obj.toString());
          fw.close();
      } catch (IOException e) {
          e.printStackTrace();
      }

  }*/
}
