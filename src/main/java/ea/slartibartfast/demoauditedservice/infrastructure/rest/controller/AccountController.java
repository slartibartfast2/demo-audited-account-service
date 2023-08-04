package ea.slartibartfast.demoauditedservice.infrastructure.rest.controller;

import ea.slartibartfast.demoauditedservice.domain.service.AccountService;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.request.AddAccountRequest;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.request.AssignAccountToConsumerRequest;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.request.UpdateAccountRequest;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.response.AddAccountResponse;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.response.RetrieveAccountResponse;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.response.UpdateAccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountService accountService;

    @GetMapping(value = "/accounts/{extId}", produces = "application/json")
    public RetrieveAccountResponse retrieve(@PathVariable("extId") String extId) {
        return accountService.retrieveAccount(extId);
    }

    @PostMapping(value = "/accounts/", produces = "application/json")
    public AddAccountResponse add(@RequestBody AddAccountRequest request) {
        return accountService.addAccount(request);
    }

    @PatchMapping(value = "/accounts/{extId}", produces = "application/json")
    public UpdateAccountResponse update(@PathVariable("extId") String extId, @RequestBody UpdateAccountRequest updateRequest) {
        return accountService.updateAccount(extId, updateRequest);
    }

    @PatchMapping(value = "/accounts/{extId}/consumer", produces = "application/json")
    public UpdateAccountResponse update(@PathVariable("extId") String extId, @RequestBody AssignAccountToConsumerRequest updateRequest) {
        return accountService.updateAccountConsumer(extId, updateRequest);
    }
}
