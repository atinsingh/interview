package co.pragra.learning.inteviewresource.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
public class Questions {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "COMPANY_ID" ,referencedColumnName = "ID")
    private Company company;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "USER_ID" , referencedColumnName = "ID")
    private User user;

    private String question;
    private String answer;

    private Instant createDate;
    private Instant updateDate;
}
