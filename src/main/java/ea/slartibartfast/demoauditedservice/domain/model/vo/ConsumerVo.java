package ea.slartibartfast.demoauditedservice.domain.model.vo;

import ea.slartibartfast.demoauditedservice.domain.model.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ConsumerVo {

    private String extId;
    private ActivityStatus status;
    private String countryCode;
    private String groupEntity;
    private String institutionId;
    private boolean highRisk;
}
