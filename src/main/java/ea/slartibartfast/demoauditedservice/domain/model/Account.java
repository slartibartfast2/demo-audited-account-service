package ea.slartibartfast.demoauditedservice.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
@Audited
@AuditOverride(forClass = AuditedEntity.class)
public class Account extends AuditedEntity {

    @Id
    @GeneratedValue(generator = "idSeqGen")
    @SequenceGenerator(name = "idSeqGen", sequenceName = "accounts_id_seq", allocationSize = 3)
    @Column(name = "id", nullable = false)
    private Long accountId;

    @Column(name = "ext_consumer_id", insertable=false, updatable=false)
    private String consumerExtId;

    @Column(name = "ext_id")
    private String extId;

    private String currencyIso;

    @Column(name = "primary_account")
    private boolean primaryAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ext_consumer_id", referencedColumnName = "ext_id")
    private Consumer consumer;

}
