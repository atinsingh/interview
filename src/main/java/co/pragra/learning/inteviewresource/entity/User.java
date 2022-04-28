package co.pragra.learning.inteviewresource.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private String firstName;
    private String lastName;

    @JsonIgnore
    private Status status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date createDate;
    private Date updateDate;

}
