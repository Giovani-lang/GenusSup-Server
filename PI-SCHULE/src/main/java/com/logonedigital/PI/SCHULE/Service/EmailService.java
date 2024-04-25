package com.logonedigital.PI.SCHULE.Service;

import com.logonedigital.PI.SCHULE.Entity.Administration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendAdminNotificationEmail(Administration admin) {
        String recipient = admin.getEcole().getEmail();
        String subject = "Bienvenue sur Genus - Vos accès";
        String content = "Cher(e) Administrateur de "+admin.getEcole().getName()+",\n" +
                "\n" +
                "Nous sommes ravis de vous accueillir au sein de la communauté Genus. Votre inscription a été effectuée avec succès, et nous sommes impatients de vous accompagner dans votre expérience avec nous.\n" +
                "\n" +
                "Pour commencer, voici vos identifiants de connexion :\n" +
                "\n" +
                "Nom d’utilisateur : " +admin.getEmail().toString()+"\n" +
                "\n" +
                " Mot de passe : admin  \n" +
                "\n" +
                "Pour vous connecter, veuillez visiter : "+"http://localhost:4200"+"\n" +
                "\n" +
                "Si vous avez des questions ou si vous avez besoin d’assistance, n’hésitez pas à contacter notre équipe de support à contact@genus.com.\n" +
                "\n" +
                "Bienvenue à bord et bonne découverte !\n" +
                "\n" +
                "Cordialement,\n" +
                "\n" +
                "L'équipe en charge de la plateforme Genus\n" +
                "\n" +
                "\n" +
                "\n" +
                "Ceci est un message automatique. Merci de ne pas y répondre.";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Genus@gmail.com");
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(content);
        this.mailSender.send(message);
    }
}