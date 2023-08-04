package ea.slartibartfast.demoauditedservice.domain.repository;

import ea.slartibartfast.demoauditedservice.domain.model.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    Optional<Consumer> findConsumerByExtId(String extId);
}
