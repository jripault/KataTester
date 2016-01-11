package org.samil.internal.security;

import org.mdkt.compiler.DynamicClassLoader;

import java.security.*;

/**
 * User: Samil
 * Date: 11/01/2016
 */
public class SandboxSecurityPolicy extends Policy {

    @Override
    public PermissionCollection getPermissions(ProtectionDomain domain) {
        if (isPlugin(domain)) {
            return pluginPermissions();
        }
        else {
            return applicationPermissions();
        }
    }

    private boolean isPlugin(ProtectionDomain domain) {
        return domain.getClassLoader() instanceof DynamicClassLoader;
    }

    private PermissionCollection pluginPermissions() {
        Permissions permissions = new Permissions(); // No permissions
        return permissions;
    }

    private PermissionCollection applicationPermissions() {
        Permissions permissions = new Permissions();
        permissions.add(new AllPermission());
        return permissions;
    }
}
