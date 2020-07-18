package org.acme.dynamodb;

import java.util.HashMap;
import java.util.Map;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

public abstract class AbstractService {

    public static final String ID_COL = "id";
    public static final String DESC_COL = "description";
    public static final String CPF_COL =  "cpf";
    public static final String RESTRICTION_CODE = "restrictionCode";
    public static final String PROCESS_ID = "processId";
    public static final String SIPF_PROFILE_CODE =  "sipfProfileCode";

    public String getTableName() {
        return "PCO_NOGORD";
    }

    protected ScanRequest scanRequest() {
        return ScanRequest.builder().tableName(getTableName())
                .attributesToGet(
                    ID_COL,
                    DESC_COL,
                    CPF_COL,
                    RESTRICTION_CODE,
                    PROCESS_ID,
                    SIPF_PROFILE_CODE
                ).build();
    }

    protected PutItemRequest putRequest(Solicitation solicitation) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put(ID_COL, AttributeValue.builder().s(solicitation.getId()).build());
        item.put(DESC_COL, AttributeValue.builder().s(solicitation.getDescription()).build());
        item.put(CPF_COL, AttributeValue.builder().s(solicitation.getCpf()).build());
        item.put(RESTRICTION_CODE, AttributeValue.builder().s(solicitation.getRestrictionCode()).build());
        item.put(PROCESS_ID, AttributeValue.builder().s(solicitation.getProcessId()).build());
        item.put(SIPF_PROFILE_CODE, AttributeValue.builder().s(solicitation.getSipfProfileCode()).build());

        return PutItemRequest.builder()
                .tableName(getTableName())
                .item(item)
                .build();
    }

    protected GetItemRequest getRequest(String cpf) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(CPF_COL, AttributeValue.builder().s(cpf).build());

        return GetItemRequest.builder()
                .tableName(getTableName())
                .key(key)
                .attributesToGet(
                    ID_COL,
                    DESC_COL,
                    CPF_COL,
                    RESTRICTION_CODE,
                    PROCESS_ID,
                    SIPF_PROFILE_CODE
                )
                .build();
    }
}
