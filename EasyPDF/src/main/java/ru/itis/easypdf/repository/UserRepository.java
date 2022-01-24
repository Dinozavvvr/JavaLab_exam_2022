package ru.itis.easypdf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.easypdf.model.User;

import java.util.Optional;

/**
 * created: 22-01-2022 - 15:31
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

}
