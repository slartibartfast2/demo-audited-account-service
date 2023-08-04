package ea.slartibartfast.demoauditedservice.domain.service;

import ea.slartibartfast.demoauditedservice.domain.model.Consumer;
import ea.slartibartfast.demoauditedservice.domain.model.vo.ConsumerVo;
import ea.slartibartfast.demoauditedservice.domain.repository.ConsumerRepository;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.request.AddConsumerRequest;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.request.UpdateConsumerRequest;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.response.AddConsumerResponse;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.response.RetrieveConsumerResponse;
import ea.slartibartfast.demoauditedservice.infrastructure.rest.controller.response.UpdateConsumerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    public RetrieveConsumerResponse retrieveConsumer(String extId) {
        ConsumerVo consumerVo = consumerRepository.findConsumerByExtId(extId)
                                                  .map(this::mapConsumerToVo)
                                                  .orElseThrow(() -> new RuntimeException("consumer cannot be found"));
        return new RetrieveConsumerResponse(consumerVo);
    }

    public AddConsumerResponse addConsumer(AddConsumerRequest addConsumerRequest) {
        var consumer = mapRequestToEntity(addConsumerRequest);
        consumer = consumerRepository.save(consumer);
        return new AddConsumerResponse(mapConsumerToVo(consumer));
    }

    private Consumer mapRequestToEntity(AddConsumerRequest request) {
        return Consumer.builder()
                       .countryCode(request.getCountryCode())
                       .highRisk(request.getHighRisk())
                       .extGroupId(request.getExtGroupId())
                       .status(request.getStatus())
                       .extId(request.getExtId())
                       .build();
    }

    private ConsumerVo mapConsumerToVo(Consumer consumer) {
        return ConsumerVo.builder()
                         .countryCode(consumer.getCountryCode())
                         .status(consumer.getStatus())
                         .groupEntity(consumer.getExtGroupId())
                         .highRisk(consumer.isHighRisk())
                         .extId(consumer.getExtId())
                         .build();
    }

    public UpdateConsumerResponse updateConsumer(String extId, UpdateConsumerRequest updateRequest) {
        var consumer = consumerRepository.findConsumerByExtId(extId).orElseThrow(() -> new RuntimeException("Consumer cannot be found!"));
        consumer.setStatus(updateRequest.getStatus());
        consumer.setCountryCode(updateRequest.getCountryCode());
        consumer.setHighRisk(updateRequest.getHighRisk());
        consumer = consumerRepository.save(consumer);
        return new UpdateConsumerResponse(mapConsumerToVo(consumer));
    }
}
