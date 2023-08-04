package ea.slartibartfast.demoauditedservice.infrastructure.rest.controller;

import ea.slartibartfast.demoauditedservice.domain.service.ConsumerService;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.request.AddConsumerRequest;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.request.UpdateConsumerRequest;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.response.AddConsumerResponse;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.response.RetrieveConsumerResponse;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.response.UpdateConsumerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ConsumerController {

    private final ConsumerService consumerService;

    @GetMapping(value = "/consumers/{extId}", produces = "application/json")
    public RetrieveConsumerResponse retrieve(@PathVariable("extId") String extId) {
        return consumerService.retrieveConsumer(extId);
    }

    @PostMapping(value = "/consumers/", produces = "application/json")
    public AddConsumerResponse add(@RequestBody AddConsumerRequest request) {
        return consumerService.addConsumer(request);
    }

    @PatchMapping(value = "/consumers/{extId}", produces = "application/json")
    public UpdateConsumerResponse update(@PathVariable("extId") String extId, @RequestBody UpdateConsumerRequest updateRequest) {
        return consumerService.updateConsumer(extId, updateRequest);
    }
}
