package ea.slartibartfast.demoauditedservice.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.*;

import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "consumers")
@Audited
@AuditOverride(forClass = AuditedEntity.class)
public class Consumer extends AuditedEntity {

    @Id
    @GeneratedValue(generator = "idSeqGen")
    @SequenceGenerator(name = "idSeqGen", sequenceName = "consumers_id_seq", allocationSize = 3)
    @Column(name = "id", nullable = false)
    private Long consumerId;

    @Column(name = "ext_id")
    private String extId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ActivityStatus status;

    @Column(name = "country_code")
    private String countryCode;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @AuditJoinTable(name = "accounts", inverseJoinColumns = @JoinColumn(name = "ext_id"))
    @OneToMany(mappedBy="consumer", fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    private Set<Account> accountList;

    @Column(name = "ext_group_id")
    private String extGroupId;

    @Column(name = "high_risk")
    private boolean highRisk;

    @Column(name = "metadata", columnDefinition = "TEXT")
    private String metaData;
}
