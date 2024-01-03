package de.fhws.fiw.fds.implementation.server.api.security;

import de.fhws.fiw.fds.sutton.server.api.security.IAuthenticationProvider;
import de.fhws.fiw.fds.sutton.server.api.security.RequiredPermission;
import de.fhws.fiw.fds.sutton.server.api.security.database.dao.IAuthDaoSupplier;
import de.fhws.fiw.fds.sutton.server.api.security.models.User;

import javax.servlet.http.HttpServletRequest;

public class NoAuthNeededAuthenticationProvider implements IAuthenticationProvider, IAuthDaoSupplier {
    @Override
    public User accessControlWithBearerToken(HttpServletRequest request, RequiredPermission requiredPermission, String... roles) {
        return new User();
    }

    @Override
    public User accessControlWithBasicAuth(HttpServletRequest request, RequiredPermission requiredPermission, String... roles) {
        return new User();
    }

    @Override
    public boolean isAdmin(User user) {
        return true;
    }
}
