package edu.fundup.test;

import edu.fundup.model.entity.Notification;
import edu.fundup.model.service.NotificationService;

public class NotificationTest {

    public static void main(String[] args) {

        Notification notification = new Notification();


        notification.setObject("object from console test");
        notification.setId_user(125);

        NotificationService ns = new NotificationService();

        ns.addNotification(notification);

    }

}
