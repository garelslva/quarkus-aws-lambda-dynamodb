package org.acme.dynamodb.ascync;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.smallrye.mutiny.Uni;
import org.acme.dynamodb.AbstractService;
import org.acme.dynamodb.Solicitation;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

@ApplicationScoped
public class AsyncService extends AbstractService {

    @Inject
    DynamoDbAsyncClient dynamoDB;

    public Uni<List<Solicitation>> findAll() {
        return Uni.createFrom().completionStage(() -> dynamoDB.scan(scanRequest()))
                .onItem().apply(res -> res.items().stream().map(Solicitation::from).collect(Collectors.toList()));
    }

    public Uni<List<Solicitation>> add(Solicitation solicitation) {
        String id = UUID.randomUUID().toString();
        solicitation.setId(id);
        return Uni.createFrom().completionStage(() -> dynamoDB.putItem(putRequest(solicitation)))
                .onItem().ignore().andSwitchTo(this::findAll);
    }

    public Uni<Solicitation> get(String cpf) {
        return Uni.createFrom().completionStage(() -> dynamoDB.getItem(getRequest(cpf)))
                .onItem().apply(resp -> Solicitation.from(resp.item()));
    }
}
