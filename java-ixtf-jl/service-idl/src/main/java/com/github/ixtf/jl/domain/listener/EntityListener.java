package com.github.ixtf.jl.domain.listener;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import static com.github.ixtf.guice.GuiceModule.getInstance;

@Slf4j
public class EntityListener {
    @Getter(lazy = true, value = AccessLevel.PRIVATE)
    private static final Mono<Service> service = _service();

    private static Mono<Service> _service() {
        return Mono.fromCallable(() -> getInstance(Service.class))
                .doOnError(e -> log.error("inject EntityListener"))
                .onErrorResume(e -> Mono.empty())
                .cache();
    }

    @PostPersist
    public void PostPersist(Object o) {
        getService().flatMap(it -> it.handlePostPersist(o)).doOnError(e -> log.error("PostPersist {}", o, e)).subscribe();
    }

    @PostUpdate
    public void PostUpdate(Object o) {
        getService().flatMap(it -> it.handlePostUpdate(o)).doOnError(e -> log.error("PostUpdate {}", o, e)).subscribe();
    }

    @PostRemove
    public void PostRemove(Object o) {
        getService().flatMap(it -> it.handlePostRemove(o)).doOnError(e -> log.error("PostRemove {}", o, e)).subscribe();
    }

    public interface Service {
        Mono<Void> handlePostPersist(Object o);

        Mono<Void> handlePostUpdate(Object o);

        Mono<Void> handlePostRemove(Object o);
    }

}
