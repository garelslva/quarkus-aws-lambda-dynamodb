package org.acme.dynamodb;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@ApplicationScoped
public class SyncService extends AbstractService {

    @Inject
    DynamoDbClient dynamoDB;

    public List<Solicitation> findAll() {
        return dynamoDB.scanPaginator(scanRequest()).items().stream()
                .map(Solicitation::from)
                .collect(Collectors.toList());
    }

    public List<Solicitation> add(Solicitation solicitation) {
        String id = UUID.randomUUID().toString();
        solicitation.setId(id);
        dynamoDB.putItem(putRequest(solicitation));
        return findAll();
    }

    public Solicitation get(String name) {
        return Solicitation.from(dynamoDB.getItem(getRequest(name)).item());
    }
}