package ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.request;

import ea.slartibartfast.demoauditedservice.domain.model.AccountType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddAccountRequest {

    private String consumerExtId;
    private String extId;
    private String currencyIso;
    private Boolean primaryAccount;
    private AccountType accountType;
}
