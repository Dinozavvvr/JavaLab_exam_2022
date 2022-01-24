package ru.itis.easypdf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * created: 22-01-2022 - 15:29
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;

    private String password;

    private String redisId;

    @Enumerated(EnumType.STRING)
    private State state;

    public enum State {
        BANNED, ACTIVE, DELETED
    }

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public enum Role {
        USER, ADMIN
    }

    public boolean isActive() {
        return state == State.ACTIVE;
    }

    public boolean isBanned() {
        return state == State.BANNED;
    }

    public boolean isDeleted() {
        return state == State.DELETED;
    }

}
