package vn.luvina.startup.config.mail;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

  @Bean
  public JavaMailSender getJavaMailSender() {
    JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
    mailSenderImpl.setHost("smtp.mailtrap.io");
    mailSenderImpl.setPort(2525);
    mailSenderImpl.setUsername("dedbea9315aadf");
    mailSenderImpl.setPassword("4080d15d8570b9");

    Properties props = mailSenderImpl.getJavaMailProperties();

    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls", "true");
    props.put("mail.debug", "true");

    return mailSenderImpl;
  }

}
