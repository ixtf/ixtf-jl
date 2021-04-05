package com.github.ixtf.jl.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.ixtf.persistence.IEntity;
import com.github.ixtf.persistence.IEntityLoggable.IOperator;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Collection;

@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Operator implements IEntity {
    @ToString.Include
    @EqualsAndHashCode.Include
    @Id
    private String id;
    @JsonIgnore
    private boolean deleted;
    @ToString.Include
    private String name;
    @JsonIgnore
    private Collection<String> pyIdx;

    public OperatorEmbeddable embeddable() {
        final var ret = new OperatorEmbeddable();
        ret.setId(id);
        ret.setName(name);
        return ret;
    }

    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    @NoArgsConstructor
    @Data
    @Embeddable
    public static class OperatorEmbeddable implements IOperator {
        @EqualsAndHashCode.Include
        private String id;
        private String name;
    }
}
