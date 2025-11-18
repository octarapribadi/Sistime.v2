package bean;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;


public class SendMail implements Serializable {

    @Resource(mappedName = "java:jboss/mail/smtpstmiktime")
    private Session mailSession;

    String toEmail;
    String subject;

    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void sendMail() {
        //FacesContext.getCurrentInstance().getExternalContext().getcon
        MimeMessage mm = new MimeMessage(mailSession);
        try {
            //mm.addHeader("Content-type", "text/html; charset=UTF-8");
            //mm.addHeader("format", "flowed");
            //mm.addHeader("Content-Transfer-Encoding", "8bit");
            mm.setFrom(new InternetAddress("no_reply@stmik-time.ac.id", "NoReply"));
            mm.setReplyTo(InternetAddress.parse("no_reply@stmik-time.ac.id", false));
            mm.setHeader("Content-Type","text/html");
            mm.setSubject(subject);
            //mm.setText(content,"UTF-8");
            mm.setContent(content,"text/html");
            mm.setSentDate(new Date());
            mm.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Transport.send(mm);
            System.out.println("mail sent!!");

        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

}
