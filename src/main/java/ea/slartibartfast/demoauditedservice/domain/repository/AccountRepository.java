package ea.slartibartfast.demoauditedservice.domain.repository;

import ea.slartibartfast.demoauditedservice.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByExtId(String extId);
}
