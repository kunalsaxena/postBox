package com.mailclient.utils;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.search.SearchTerm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckSpam {

  private static Set<String> spamAddresses = new HashSet<>();
  private static Logger logger = LogManager.getLogger(CheckSpam.class);

  /** Checking Spam email in Gmail Account and delete it. */
  public static void checkSpam() {

    try {
      logger.info("Fetching from Gmail");

      // create properties field
      Properties properties = new Properties();
      properties.put("mail.imaps.host", "imap.gmail.com");
      properties.put("mail.imaps.port", "993");
      properties.put("mail.imaps.starttls.enable", "true");

      Session emailSession = Session.getInstance(properties, null);
      Store store = emailSession.getStore("imaps");
      store.connect("kunal.saxena@gmail.com", "password");

      // create the folder object and open it
      Folder folder = store.getFolder("[Gmail]/Spam");
      folder.open(Folder.READ_WRITE);
      SearchTerm term =
          new SearchTerm() {

            private static final long serialVersionUID = 7843258886242128575L;

            @Override
            public boolean match(Message msg) {
              InternetAddress from;
              try {
                from = (InternetAddress) msg.getFrom()[0];
                if (spamAddresses.contains(from.getAddress())) {
                  return true;
                }
              } catch (MessagingException e) {
                logger.error("Error:- ", e.getMessage());
              }
              return false;
            }
          };

      long startTime = System.currentTimeMillis();

      Message[] searchResult = folder.search(term);
      logger.info(searchResult.length + " matching spams found");

      for (Message msg : searchResult) {
        msg.setFlag(Flags.Flag.DELETED, true);
      }

      long endTime = System.currentTimeMillis();

      // close the store and folder objects
      logger.info("Time taken in millies " + (endTime - startTime));

      folder.close(true);
      store.close();

    } catch (NoSuchProviderException e) {
      e.printStackTrace();
    } catch (MessagingException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static Set<String> createSpamList() {

    spamAddresses.add("newsletter@em.maxfashion.in");
    spamAddresses.add("info@displaymailbox.com");
    spamAddresses.add("pinbot@explore.pinterest.com");
    spamAddresses.add("info@jmails.info");
    spamAddresses.add("newsletters@m2i.in");
    spamAddresses.add("alert@mailmx.in");
    spamAddresses.add("99@e.2mg.in");
    spamAddresses.add("gdyck@tradal.net");
    spamAddresses.add("learning.team@naukri.com");
    spamAddresses.add("annee@marce.bbbrq.com");
    spamAddresses.add("newsletters@wimpmail.com");
    spamAddresses.add("noreply@zohoaccounts.com");
    spamAddresses.add("ibmcybersecurity@kestone.biz");
    spamAddresses.add("hello@haptik.co");
    spamAddresses.add("info@commtrends.in");
    spamAddresses.add("newsletters@ibankserviceonline.com");
    spamAddresses.add("info@todaymstr.com");
    spamAddresses.add("newsletters@ibankserviceonline.com");
    spamAddresses.add("dr_occhitmudgal@t-online.de");
    spamAddresses.add("allflash@kestone.biz");
    spamAddresses.add("99@a.chtah.com");
    spamAddresses.add("hello@haptik.co");
    spamAddresses.add("mshelenwagon@air.ocn.ne.jp");
    spamAddresses.add("sanchez802913@ub.edu");
    spamAddresses.add("saibaba@aspirecareers.in");
    spamAddresses.add("winifrede@beckye.dddah.com");
    spamAddresses.add("movies@cinepolisindia.in");
    spamAddresses.add("crm@em.oyorooms.com");
    spamAddresses.add("alerts@moneycontrolupdates.com");
    spamAddresses.add("jobs@techgig.com");
    spamAddresses.add("assessment@techgig.com");
    spamAddresses.add("fujita@math.meiji.ac.jp");
    spamAddresses.add("Dell_India_Marketing@dell.com");
    spamAddresses.add("update@email-smtp1.com");
    spamAddresses.add("do_not_reply@bigrock.com");
    spamAddresses.add("info@send.grammarly.com");
    spamAddresses.add("DellEMCevent@kestone.biz");
    spamAddresses.add("irctctourismoffers@irctc.co.in");
    spamAddresses.add("update@offersurprise.com");
    spamAddresses.add("salary2wealth@kotak.in");
    spamAddresses.add("contact@cbcinewsapp.com");
    spamAddresses.add("support@vouchrcodes.com");
    spamAddresses.add("naukrirecruiteralerts@naukri.com");
    spamAddresses.add("info@es.finance-plus.in");
    spamAddresses.add("info@qbresearch.com");
    spamAddresses.add("hello@mailerassist.com");
    spamAddresses.add("news2018-february@mailer.altova.com");
    spamAddresses.add("invite@dock.io");
    spamAddresses.add("info@es.finance-plus.in");
    spamAddresses.add("expertspeak@techgig.com");
    spamAddresses.add("ert@timesjobs.com");
    spamAddresses.add("pinbot@explore.pinterest.com");
    spamAddresses.add("notification@referhire.com");
    spamAddresses.add("technews@techgig.com");
    spamAddresses.add("assessment@techgig.com");
    spamAddresses.add("hello@haptik.co");
    spamAddresses.add("support@vouvhrcodes.com");
    spamAddresses.add("newsletters@magicmails.in");
    spamAddresses.add("tara.desai@mapmyindia.in");
    spamAddresses.add("pinbot@notifications.pinterest.com");
    spamAddresses.add("urbanpro-no-reply@urbanpro.com");
    spamAddresses.add("info@freepress.net");
    spamAddresses.add("support@sitepoint.com");
    spamAddresses.add("jobs@myamcat.com");
    spamAddresses.add("site2sms");
    spamAddresses.add("facts@facts.axandra.com");
    spamAddresses.add("support@addthis.com");
    spamAddresses.add("support@vkarma.com");
    spamAddresses.add("info@serv2.stengineers.info");
    spamAddresses.add("support@peopleg.com");
    spamAddresses.add("newsletters@shopatbest.com");
    spamAddresses.add("gudiya.gateforum@gmail.com");
    spamAddresses.add("contact@cbcinewsemail.com");
    spamAddresses.add("info@timesjobs.com");
    spamAddresses.add("noreply@updates.freelancer.com");
    spamAddresses.add("circles@lcpostmail.com");
    spamAddresses.add("noreply+feedproxy@google.com");
    spamAddresses.add("support@golfmy.com");
    spamAddresses.add("irctcrailconnect@irctc.co.in");
    spamAddresses.add("daniel.graziano@sitepoint.com");
    spamAddresses.add("e-alerts@brinkpro.com");
    spamAddresses.add("expertspeak@techgig.com");
    spamAddresses.add("technews@techgig.com");
    spamAddresses.add("info@24minds.in");
    spamAddresses.add("indiasalesteam@microhosting.in");
    spamAddresses.add("info@mojodesks.in");
    spamAddresses.add("contact@topicsontechmail.com");
    spamAddresses.add("cloudonair@theyounion.in");
    spamAddresses.add("support@99jet.com");
    spamAddresses.add("aniruddha.sen@mail.cignattkonline.in");
    spamAddresses.add("support@indiadz.com");
    spamAddresses.add("resumeegxkew5hbwljcy5jb20=@naukri.com");
    spamAddresses.add("support@localcirclesemail.com");
    spamAddresses.add("newsletter@offeronmail.com");
    spamAddresses.add("admin@pracharhub.com");
    spamAddresses.add("ittoolboxalerts@ittoolbox.com");
    spamAddresses.add("noreply@activesearchresults.com");
    spamAddresses.add("fenestawindows@goodthng.com");
    spamAddresses.add("info@timesjobs.com");
    spamAddresses.add("contact@cbcinewsemail.com");
    spamAddresses.add("noreply@updates.freelancer.com");
    spamAddresses.add("admin@finestshopper.in");
    spamAddresses.add("satvilkar@ip2.deliverybox32.crabemail.in");
    spamAddresses.add("newsletter@coupondunia.in");
    spamAddresses.add("news@xara.com");
    spamAddresses.add("noreply@activesearchresults.com");
    spamAddresses.add("news@readwritecars.com");
    spamAddresses.add("ittoolboxalerts@ittoolbox.com");
    spamAddresses.add("support@kiddybazaar.com");
    spamAddresses.add("jobbuzz@timesjobs.com");
    spamAddresses.add("info@monsterindia.com");
    spamAddresses.add("promo@naukri.com");
    spamAddresses.add("virtual.reality@readwrite.us");
    spamAddresses.add("support@bargainmint.com");
    spamAddresses.add("support@golfmy.com");
    spamAddresses.add("vimeo@email.vimeo.com");
    spamAddresses.add("emily@topresume.com");
    spamAddresses.add("info@serv3.technocart.info");
    spamAddresses.add("alerts@magicbricks.co");
    spamAddresses.add("noreplies@academia-mail.co");
    spamAddresses.add("newsletter@carnivalcinemas.co");
    spamAddresses.add("@perfectmoney.i");
    spamAddresses.add("brinkpr");
    spamAddresses.add("toplevelmai");
    spamAddresses.add("info@eckmarket.i");
    spamAddresses.add("@toplevelmai");
    spamAddresses.add("@ivyeducated.co");
    spamAddresses.add("@spe.zyngamail.co");
    spamAddresses.add("@freedealcode.i");
    spamAddresses.add("loans@kotak.i");
    spamAddresses.add("@offer4uhub.co");
    spamAddresses.add("@lucanlorenzo.co");
    spamAddresses.add("notification@referhire.co");
    spamAddresses.add("@101coupon.i");
    spamAddresses.add("@bankmarket.i");
    spamAddresses.add("@hotoffers.co.i");
    spamAddresses.add("@robomart.co");
    spamAddresses.add("promotion@techgig.co");
    spamAddresses.add("newsletters@smtpmailbox.co");
    spamAddresses.add("@email.toolbox.co");
    spamAddresses.add("@mc.shiningindiaa.co");
    spamAddresses.add("@perfectinbox.co");
    spamAddresses.add("@wally.m");
    spamAddresses.add("@viralnewslife.co");
    spamAddresses.add("@gagcoolapp.co");
    spamAddresses.add("@shoes499.co");
    spamAddresses.add("@christine.sipb.c");
    spamAddresses.add("@haptik.c");
    spamAddresses.add("promo@naukri.co");
    spamAddresses.add("hello@jamendo.co");
    spamAddresses.add("@picklemastimail.co");
    spamAddresses.add("@serv1.stengineers4.inf");
    spamAddresses.add("noreply@gplusmail.co");
    spamAddresses.add("service@naukri.co");
    spamAddresses.add("@yechi.vkatie.co");
    spamAddresses.add("assessment@techgig.co");
    spamAddresses.add("@enlimbo.co");
    spamAddresses.add("@finestshopper.i");
    spamAddresses.add("@namomail.co");
    spamAddresses.add("@cm.healthinsurancemailer.co");
    spamAddresses.add("@freekacharg");
    spamAddresses.add("@door2inbox.co");
    spamAddresses.add("technews@techgig.co");
    spamAddresses.add("contest@techgig.co");
    spamAddresses.add("@thalizma.co");
    spamAddresses.add("@email.toolbox.com");
    spamAddresses.add("@e.moneyctlr.co");
    return spamAddresses;
  }

  public static void main(String[] args) {
    createSpamList();
    checkSpam();
  }
}
