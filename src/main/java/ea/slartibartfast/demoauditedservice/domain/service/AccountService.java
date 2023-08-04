package ea.slartibartfast.demoauditedservice.domain.service;

import ea.slartibartfast.demoauditedservice.domain.model.Account;
import ea.slartibartfast.demoauditedservice.domain.model.Consumer;
import ea.slartibartfast.demoauditedservice.domain.model.vo.AccountVo;
import ea.slartibartfast.demoauditedservice.domain.repository.AccountRepository;
import ea.slartibartfast.demoauditedservice.domain.repository.ConsumerRepository;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.request.AddAccountRequest;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.request.AssignAccountToConsumerRequest;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.request.UpdateAccountRequest;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.response.AddAccountResponse;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.response.RetrieveAccountResponse;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.response.UpdateAccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final ConsumerRepository consumerRepository;

    public RetrieveAccountResponse retrieveAccount(String extId) {
        AccountVo accountVo = accountRepository.findAccountByExtId(extId)
                                               .map(this::mapAccountToVo)
                                               .orElseThrow(() -> new RuntimeException("account cannot be found"));

        return new RetrieveAccountResponse(accountVo);
    }

    private AccountVo mapAccountToVo(Account account) {
        return AccountVo.builder()
                        .accountType(account.getAccountType())
                        .primaryAccount(account.isPrimaryAccount())
                        .consumerExtId(account.getConsumerExtId())
                        .currencyIso(account.getCurrencyIso())
                        .extId(account.getExtId())
                        .build();
    }

    public AddAccountResponse addAccount(AddAccountRequest request) {
        var consumer = consumerRepository.findConsumerByExtId(request.getConsumerExtId())
                                         .orElseThrow(() -> new RuntimeException("Consumer cannot be found!"));
        var account = mapRequestToEntity(request, consumer);
        account = accountRepository.save(account);
        return new AddAccountResponse(mapAccountToVo(account));
    }

    private Account mapRequestToEntity(AddAccountRequest request, Consumer consumer) {
        return Account.builder()
                      .accountType(request.getAccountType())
                      .primaryAccount(request.getPrimaryAccount())
                      .consumerExtId(request.getConsumerExtId())
                      .extId(request.getExtId())
                      .currencyIso(request.getCurrencyIso())
                      .consumer(consumer)
                      .build();
    }

    public UpdateAccountResponse updateAccount(String extId, UpdateAccountRequest updateRequest) {
        var account = accountRepository.findAccountByExtId(extId).orElseThrow(() -> new RuntimeException("Account cannot be found!"));
        account.setCurrencyIso(updateRequest.getCurrencyIso());
        account.setAccountType(updateRequest.getAccountType());
        account.setPrimaryAccount(updateRequest.getPrimaryAccount());
        account = accountRepository.save(account);
        return new UpdateAccountResponse(mapAccountToVo(account));
    }

    public UpdateAccountResponse updateAccountConsumer(String extId, AssignAccountToConsumerRequest updateRequest) {
        var consumer = consumerRepository.findConsumerByExtId(updateRequest.getConsumerExtId()).orElseThrow(() -> new RuntimeException("Consumer cannot be found!"));
        var account = accountRepository.findAccountByExtId(extId).orElseThrow(() -> new RuntimeException("Account cannot be found!"));
        account.setConsumerExtId(updateRequest.getConsumerExtId());
        account.setConsumer(consumer);
        account = accountRepository.save(account);
        return new UpdateAccountResponse(mapAccountToVo(account));
    }
}
