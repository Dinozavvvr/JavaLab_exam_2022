package ru.itis.easypdf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.easypdf.model.Document;

/**
 * created: 24-01-2022 - 14:19
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

}
