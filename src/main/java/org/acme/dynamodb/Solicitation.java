package org.acme.dynamodb;

import java.util.Map;
import java.util.Objects;

import io.quarkus.runtime.annotations.RegisterForReflection;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@RegisterForReflection
public class Solicitation {

    private String id;
    private String description;
    private String cpf;
    private String restrictionCode;
    private String processId;
    private String sipfProfileCode;

    public Solicitation() {
    }

    public static Solicitation from(Map<String, AttributeValue> item) {
        Solicitation solicitation = new Solicitation();
        if (item == null && item.isEmpty()) {
            return solicitation;
        }
        solicitation.setId(item.get(AbstractService.ID_COL).s());
        solicitation.setDescription(item.get(AbstractService.DESC_COL).s());
        solicitation.setCpf(item.get(AbstractService.CPF_COL).s());
        solicitation.setProcessId(item.get(AbstractService.PROCESS_ID).s());
        solicitation.setRestrictionCode(item.get(AbstractService.RESTRICTION_CODE).s());
        solicitation.setSipfProfileCode(item.get(AbstractService.SIPF_PROFILE_CODE).s());

        return solicitation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRestrictionCode() {
        return restrictionCode;
    }

    public void setRestrictionCode(String restrictionCode) {
        this.restrictionCode = restrictionCode;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getSipfProfileCode() {
        return sipfProfileCode;
    }

    public void setSipfProfileCode(String sipfProfileCode) {
        this.sipfProfileCode = sipfProfileCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Solicitation)) {
            return false;
        }

        Solicitation other = (Solicitation) obj;

        return Objects.equals(other.cpf, this.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.cpf);
    }
}