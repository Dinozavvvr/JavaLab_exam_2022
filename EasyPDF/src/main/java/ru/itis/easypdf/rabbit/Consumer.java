package ru.itis.easypdf.rabbit;

/**
 * created: 23-01-2022 - 17:57
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
public interface Consumer<T> {

    void receiveMessage(T message);

}

