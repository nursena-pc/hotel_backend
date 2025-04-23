package com.antalyaotel.service;
import com.antalyaotel.model.Reservation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;



@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        try {
            System.out.println("📨 E-posta gönderme işlemi başlıyor: " + to);


            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            message.setFrom("otelsmtp@gmail.com");
            mailSender.send(message);
            System.out.println("✅ E-posta başarıyla gönderildi: " + to);
        }catch(Exception e){
            System.err.println("❌ E-posta gönderme hatası: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void sendReservationConfirmation(String to, Reservation reservation) {
        String subject = "Rezervasyon Onayı - " + reservation.getRoom().getId();
        String text = "Sayın " + reservation.getUser().getUsername() + ",\n\n" +
                "Rezervasyonunuz başarıyla oluşturuldu.\n\n" +
                "Oda Numarası: " + reservation.getRoom().getId() + "\n" +
                "Giriş Tarihi: " + reservation.getStartDate() + "\n" +
                "Çıkış Tarihi: " + reservation.getEndDate() + "\n\n" +
                "Teşekkürler,\nAntalya Otel";

        sendEmail(to, subject, text);
    }


}