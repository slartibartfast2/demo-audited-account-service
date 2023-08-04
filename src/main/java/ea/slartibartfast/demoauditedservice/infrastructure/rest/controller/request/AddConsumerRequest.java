package ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.request;

import ea.slartibartfast.demoauditedservice.domain.model.ActivityStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddConsumerRequest {

    private String extId;
    private ActivityStatus status;
    private String countryCode;
    private String extGroupId;
    private Boolean highRisk;
}
