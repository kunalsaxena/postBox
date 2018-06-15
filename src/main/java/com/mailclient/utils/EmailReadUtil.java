package com.mailclient.utils;

import com.mailclient.dto.EmailDTO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Created by kunal on 22/2/17. */
public class EmailReadUtil {

  public static List<EmailDTO> readInbox() {
    List<EmailDTO> emailList = new ArrayList<>();
    try {
      BufferedReader br = new BufferedReader(new FileReader("/home/kunal/postbox/inbox/inbox"));
      String line = null;

      while ((line = br.readLine()) != null) {

        if ("".equals(line.trim())) {
          continue;
        }

        if (line.contains("Hash :: ")) {
          String from = br.readLine().replace("From :: ", "");
          String subject = br.readLine().replace("Subject :: ", "");
          String date = br.readLine().replace("Date :: ", "");
          String contentType = br.readLine().replace("Content Type :: ", "");

          StringBuilder content = new StringBuilder();
          String contentLine = null;
          while (!(contentLine = br.readLine()).contains("End Content")) {
            if (contentLine.contains("oundary")) continue;
            content.append(contentLine);
            content.append("<br />");
          }
          String finalContent = content.toString().replace("Content :: ", "");

          EmailDTO emailDTO = new EmailDTO();
          emailDTO.setFrom(from);
          emailDTO.setSubject(subject);
          emailDTO.setReceivedDate(System.currentTimeMillis());
          emailDTO.setContentType(contentType);
          emailDTO.setContent(finalContent);
          emailList.add(emailDTO);
          emailDTO = null;
        }
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return emailList;
  }

  public static void main(String args[]) {
    List<EmailDTO> emails = readInbox();
    for (EmailDTO email : emails) {
      System.out.println(email);
    }
  }
}
