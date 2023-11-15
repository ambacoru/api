package ru.ambaco.cmr.email;

import ru.ambaco.cmr.entities.User;

public interface EmailSender {
    void sendEmail(User user);
}