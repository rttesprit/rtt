package edu.fundup.model.iservice;

import edu.fundup.model.entity.Notification;

import java.util.ArrayList;
import java.util.Date;

public interface INotificationService {
    void addNotification(Notification notification);
    void markNotificationAsRead(Notification notification);
    ArrayList<Notification> getNotifications();

    ArrayList<Notification> findNotificationByIdUser(int idUser);
    ArrayList<Notification> findNotificationByTime(Date date);
    ArrayList<Notification> getUnreadNotifications();

}
