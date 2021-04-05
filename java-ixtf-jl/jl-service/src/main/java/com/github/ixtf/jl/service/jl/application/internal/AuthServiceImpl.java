package com.github.ixtf.jl.service.jl.application.internal;

import com.github.ixtf.jl.domain.Operator;
import com.github.ixtf.jl.service.jl.application.AuthService;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;

import java.security.Principal;

@Slf4j
@Singleton
public class AuthServiceImpl implements AuthService {
    @Override
    public Mono<Operator> find(Principal principal) {
        final var operator = new Operator();
        operator.setId(ObjectId.get().toHexString());
        operator.setName("AuthServiceImpl");
        return Mono.just(operator);
    }
}
