package com.github.ixtf.jl.service.report.application.internal;

import com.github.ixtf.api.ApiContext;
import com.github.ixtf.jl.service.report.application.DashboardService;
import reactor.core.publisher.Mono;

import java.util.Map;

public class DashboardServiceImpl implements DashboardService {
    @Override
    public Mono<Map> get(ApiContext ac) {
        return Mono.just(Map.of("DashboardServiceImpl", "DashboardServiceImpl"));
    }
}
