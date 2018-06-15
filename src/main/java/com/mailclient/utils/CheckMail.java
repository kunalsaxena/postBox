package com.mailclient.utils;

import com.mailclient.dto.EmailDTO;
import com.sun.mail.pop3.POP3Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.io.*;
import java.util.*;

/** Created by kunal on 10/2/17. */
public class CheckMail {

  private static Logger logger = LogManager.getLogger(CheckMail.class);

  public static List<EmailDTO> check() {

    List<EmailDTO> emailList = new ArrayList<>();
    try {

      logger.info("Fetching from Gmail");

      // create properties field
      Properties properties = new Properties();
      properties.put("mail.pop3s.host", PBConstants.HOST);
      properties.put("mail.pop3s.port", PBConstants.PORT);
      properties.put("mail.pop3s.starttls.enable", "true");

      Map<String, Object> map = JWTTokenUtil.parseToken(PBUtils.readToken());

      // Setup authentication, get session
      Session emailSession =
          Session.getInstance(
              properties,
              new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(map.get("username").toString(), "Kunal22@");
                }
              });

      // emailSession.setDebug(true);

      // create the POP3 store object and connect with the pop server
      Store store = emailSession.getStore(PBConstants.MAIL_STORE_TYPE);

      store.connect();

      // create the folder object and open it
      Folder emailFolder = store.getFolder("INBOX");
      emailFolder.open(Folder.READ_ONLY);

      // retrieve the messages from the folder in an array and print it
      Message[] messages = emailFolder.getMessages();
      System.out.println("messages.length---" + messages.length);

      for (int i = 0, n = 50; i < n; i++) {
        POP3Message message = (POP3Message) messages[i];

        EmailDTO email = new EmailDTO();
        email.setSubject(message.getSubject());
        StringBuilder sb = new StringBuilder();
        //                writePart(message, sb);

        String content = getPartContent(message);

        Address[] fromAddresses = message.getFrom();
        InternetAddress addr = (InternetAddress) fromAddresses[0];
        //              System.out.println(addr.getAddress() + " and " + addr.getPersonal());
        email.setFrom(addr.getAddress());
        email.setContentType(message.getContentType());
        email.setContent(content);
        email.setReceivedDate(System.currentTimeMillis());
        emailList.add(email);
        System.out.println("processing message no :- " + i);
      }

      //            PBUtils.changeConfigObject("gmailLastSyncedEmailNo", messages[messages.length -
      // 1].getMessageNumber());

      // write emails to file
      MailWriteUtil mailWriteUtil = new MailWriteUtil();
      mailWriteUtil.writeEmail(emailList);

      // close the store and folder objects
      emailFolder.close(false);
      store.close();

    } catch (NoSuchProviderException e) {
      e.printStackTrace();
    } catch (MessagingException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      return emailList;
    }
  }

  /*
   * This method checks for content-type
   * based on which, it processes and
   * fetches the content of the message
   */
  public static StringBuilder writePart(Part p, StringBuilder sb) throws Exception {

    System.out.println("CONTENT-TYPE: " + p.getContentType());

    // check if the content is plain text
    if (p.isMimeType("text/plain")) {
      sb.append(p.getContent());
    }

    /*// Get attachment method
    else if (p.getContentType().contains("multipart")) {

        System.out.println("This is a Multipart");

        List<File> attachments = new ArrayList<File>();
        Multipart multipart = (Multipart) p.getContent();

        for (int i = 0; i < multipart.getCount(); i++) {

            BodyPart bodyPart = multipart.getBodyPart(i);
            if(!Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition()) && StringUtils.isBlank(bodyPart.getFileName())) {
                continue; // dealing with attachments only
            }
            InputStream is = bodyPart.getInputStream();
            File f = new File("/tmp/" + bodyPart.getFileName());
            FileOutputStream fos = new FileOutputStream(f);
            byte[] buf = new byte[4096];
            int bytesRead;
            while((bytesRead = is.read(buf))!=-1) {
                fos.write(buf, 0, bytesRead);
            }
            fos.close();
            attachments.add(f);
        }
    }*/

    // check if the content has attachment
    //        else if (p.isMimeType("multipart/alternative")) {
    else if (p.getContentType().contains("multipart")) {

      System.out.println("This is a Multipart");

      Multipart mp = (Multipart) p.getContent();
      int count = mp.getCount();
      for (int i = 0; i < count; i++) writePart(mp.getBodyPart(i), sb);
    }

    // check if the content is a nested message
    else if (p.isMimeType("message/rfc822")) {

      System.out.println("This is a Nested Message");

      writePart((Part) p.getContent(), sb);
    }

    // check if the content is an inline image
    else if (p.isMimeType("image/jpeg")) {
      System.out.println("--------> image/jpeg");
      Object o = p.getContent();

      InputStream x = (InputStream) o;

      // Construct the required byte array
      System.out.println("x.length = " + x.available());
      byte[] bArray = new byte[x.available()];
      int i = 0;

      while ((i = (int) ((InputStream) x).available()) > 0) {
        int result = (int) (((InputStream) x).read(bArray));
        if (result == -1) break;
      }
      FileOutputStream f2 = new FileOutputStream("/tmp/image.jpg");
      f2.write(bArray);
      //            sb.append(bArray);
    } else if (p.getContentType().contains("image/")) {
      System.out.println("content type" + p.getContentType());
      File f = new File("image" + new Date().getTime() + ".jpg");
      DataOutputStream output =
          new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
      com.sun.mail.util.BASE64DecoderStream test =
          (com.sun.mail.util.BASE64DecoderStream) p.getContent();
      byte[] buffer = new byte[1024];
      int bytesRead;
      while ((bytesRead = test.read(buffer)) != -1) {
        output.write(buffer, 0, bytesRead);
        //                sb.append(buffer);
      }
    } else {
      Object o = p.getContent();
      if (o instanceof String) {
        System.out.println("This is a string");
        System.out.println("---------------------------");
        System.out.println((String) o);

        sb.append(o);
      } else if (o instanceof InputStream) {
        System.out.println("This is just an input stream");
        System.out.println("---------------------------");
        InputStream is = (InputStream) o;
        is = (InputStream) o;
        int c;
        while ((c = is.read()) != -1) {
          System.out.write(c);

          //                    sb.append(c);
        }

      } else {
        System.out.println("This is an unknown type");
        System.out.println("---------------------------");
        System.out.println(o.toString());

        sb.append(o.toString());
      }
    }

    return sb;
  }

  /** Return the primary text content of the message. */
  public static String getPartContent(Part p) throws MessagingException, IOException {
    if (p.isMimeType("text/*")) {
      String s = (String) p.getContent();
      return s;
    }

    if (p.isMimeType("multipart/alternative")) {
      // prefer html text over plain text
      Multipart mp = (Multipart) p.getContent();
      String text = null;
      for (int i = 0; i < mp.getCount(); i++) {
        Part bp = mp.getBodyPart(i);
        if (bp.isMimeType("text/plain")) {
          if (text == null) text = getPartContent(bp);
          continue;
        } else if (bp.isMimeType("text/html")) {
          String s = getPartContent(bp);
          if (s != null) return s;
        } else {
          return getPartContent(bp);
        }
      }
      return text;
    } else if (p.isMimeType("multipart/*")) {
      Multipart mp = (Multipart) p.getContent();
      for (int i = 0; i < mp.getCount(); i++) {
        String s = getPartContent(mp.getBodyPart(i));
        if (s != null) return s;
      }
    }

    return null;
  }

  public static void main(String args[]) {
    List<EmailDTO> check = check();
    System.out.print(check);
  }
}
