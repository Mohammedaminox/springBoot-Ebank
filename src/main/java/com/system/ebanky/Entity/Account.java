package com.system.ebanky.Entity;
import com.system.ebanky.Entity.Enums.AccountStatus;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue
    private long id;
    private Double balance;
    private AccountStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
