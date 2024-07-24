package com.genus.GENUS_PRIMO.Service;

import com.genus.GENUS_PRIMO.Entity.Administration;
import com.genus.GENUS_PRIMO.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


    // Function to send email notification to an admin about account created

    public void sendAdminNotificationEmail(Administration admin) {
        try {
            sendEmail(admin);
        } catch (MailException e) {
            // If the first attempt fails, schedule a retry after 3 minutes
            retryEmail(admin, 1);
        }
    }

    private void sendEmail(Administration admin) {
        String recipient = admin.getEcole().getEmail();
        String subject = "Bienvenue sur Genus - Vos accès";
        String content = "Cher(e) Administrateur de " + admin.getEcole().getName() + ",\n" +
                "\n" +
                "Nous sommes ravis de vous accueillir au sein de la communauté Genus. Votre inscription a été effectuée avec succès, et nous sommes impatients de vous accompagner dans votre expérience avec nous.\n" +
                "\n" +
                "Pour commencer, voici vos identifiants de connexion :\n" +
                "\n" +
                "Nom d’utilisateur : " + admin.getEmail().toString() + "\n" +
                "\n" +
                "Mot de passe : admin \n" +
                "\n" +
                "Pour vous connecter, veuillez visiter : " + "http://localhost:4200" + "\n" +
                "\n" +
                "Si vous avez des questions ou si vous avez besoin d’assistance, n’hésitez pas à contacter notre équipe de support à contact@genus.com.\n" +
                "\n" +
                "Bienvenue à bord et bonne découverte !\n" +
                "\n" +
                "Cordialement,\n" +
                "\n" +
                "L'équipe en charge de la plateforme Genus\n" +
                "\n" +
                "Ceci est un message automatique. Merci de ne pas y répondre.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Genus@gmail.com");
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    @Async
    public void retryEmail(Administration admin, int attempt) {
        long delay = (attempt == 1) ? 180000 : 300000; // 3 minutes for first retry, 5 minutes for second retry

        try {
            Thread.sleep(delay);
            sendEmail(admin);
        } catch (MailException | InterruptedException e) {
            if (attempt < 3) {
                retryEmail(admin, attempt + 1);
            } else {
                // Log the failure or handle the failure accordingly
                System.err.println("Failed to send email after 3 attempts");
            }
        }
    }

    // Function to send email notification to a student about account activity

    public void sendUserNotificationEmail(User user) {
        try {
            sendUserEmail(user);
        } catch (MailException e) {
            // If the first attempt fails, schedule a retry after 3 minutes
            retryUserEmail(user, 1);
        }
    }
    private void sendUserEmail(User user) {
        String recipient = user.getEmail();
        String subject = "Notification concernant une mise à jour sur votre compte Genus";
        String content = "Bonjour"+ " " +(user.getGenre() == "M" ? "Mme" : "Mr")+ " " + user.getPrenom() + " " + user.getNom() + ",\n" +
                "\n" +
                "Nous vous informons qu'une mise à jour a été effectuée sur votre compte Genus.\n" +
                "\n" +
                "Vous pouvez consulter votre compte Genus pour visualiser cette mise à jour et d'autres informations importantes concernant votre scolarité.\n" +
                "\n" +
                "Accéder à votre compte : \n" +
                "\n" +
                "http://localhost:4200" + "\n" +
                "\n" +
                "Cordialement,\n" +
                "\n" +
                "L'équipe en charge de la plateforme Genus\n" +
                "\n" +
                "Ceci est un message automatique. Merci de ne pas y répondre.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Genus@gmail.com");
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    @Async
    public void retryUserEmail(User user, int attempt) {
        long delay = (attempt == 1) ? 180000 : 300000; // 3 minutes for first retry, 5 minutes for second retry

        try {
            Thread.sleep(delay);
            sendUserEmail(user);
        } catch (MailException | InterruptedException e) {
            if (attempt < 3) {
                retryUserEmail(user, attempt + 1);
            } else {
                // Log the failure or handle the failure accordingly
                System.err.println("Failed to send email after 3 attempts");
            }
        }
    }
}
