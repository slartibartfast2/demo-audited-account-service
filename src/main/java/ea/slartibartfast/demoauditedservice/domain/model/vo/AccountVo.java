package ea.slartibartfast.demoauditedservice.domain.model.vo;

import ea.slartibartfast.demoauditedservice.domain.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AccountVo {

    private String consumerExtId;
    private String extId;
    private String currencyIso;
    private boolean primaryAccount;
    private AccountType accountType;
    private String institutionExtId;
}
