    package com.my.portalDemandas_api.security;

    public record AuthenticationDto(
            String email,
            String password
    ) {
    }
