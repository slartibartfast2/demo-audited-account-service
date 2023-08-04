package ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AssignAccountToConsumerRequest {
    private String consumerExtId;
}
