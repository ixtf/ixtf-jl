package com.github.ixtf.jl.service.jl.application.internal;

import com.github.ixtf.jl.domain.listener.EntityListener;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Singleton
public class EntityListenersServiceImpl implements EntityListener.Service {
    @Override
    public Mono<Void> handlePostPersist(Object o) {
        return Mono.empty();
    }

    @Override
    public Mono<Void> handlePostUpdate(Object o) {
        return Mono.empty();
    }

    @Override
    public Mono<Void> handlePostRemove(Object o) {
        return Mono.empty();
    }
}
