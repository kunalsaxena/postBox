package com.mailclient.utils;

import com.mailclient.dto.EmailDTO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/** Created by kunal on 21/2/17. */
public class MailWriteUtil {

  /**
   * Email format will be :-
   *
   * <p>----------------hash
   *
   * <p>From : Subject : Time : Contant :
   */
  public void writeEmail(List<EmailDTO> emailDTOList) throws IOException {
    BufferedWriter br = new BufferedWriter(new FileWriter("/home/kunal/postbox/inbox/inbox", true));
    for (EmailDTO email : emailDTOList) {
      String emailHash = PBUtils.generateEmailHash(email.getFrom(), email.getReceivedDate());

      br.newLine();
      br.newLine();

      br.write("Hash :: --------------------" + emailHash);
      br.newLine();

      br.write("From :: " + email.getFrom());
      br.newLine();

      br.write("Subject :: " + email.getSubject());
      br.newLine();

      br.write("Date :: " + email.getReceivedDate().toString());
      br.newLine();

      br.write("Content Type :: " + email.getContentType());
      br.newLine();

      br.write("Content :: " + email.getContent());
      br.newLine();
      br.write("End Content");
      br.newLine();
    }

    br.flush();
    br.close();
  }
}
