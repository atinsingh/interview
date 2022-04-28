package co.pragra.learning.inteviewresource.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
public class Company {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String companyName;
    private String location;
    private Status status;
    private Instant createDate;
    private Instant updateDate;
}
