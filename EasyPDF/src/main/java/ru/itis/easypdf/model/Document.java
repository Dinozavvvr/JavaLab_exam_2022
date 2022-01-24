package ru.itis.easypdf.model;

import lombok.*;

import javax.persistence.*;

/**
 * created: 24-01-2022 - 14:16
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
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private Long createdAt;

    @ToString.Exclude
    private byte[] data;

}
