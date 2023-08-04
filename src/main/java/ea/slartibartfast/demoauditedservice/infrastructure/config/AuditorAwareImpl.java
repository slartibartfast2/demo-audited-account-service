package ea.slartibartfast.demoauditedservice.infrastructure.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    private static final String SYSTEM = "System";

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SYSTEM);
    }
}
