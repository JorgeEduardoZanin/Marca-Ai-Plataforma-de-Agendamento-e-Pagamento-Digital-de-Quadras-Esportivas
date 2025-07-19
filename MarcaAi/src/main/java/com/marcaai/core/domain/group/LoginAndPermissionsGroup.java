package com.marcaai.core.domain.group;

import com.marcaai.core.domain.Login;
import com.marcaai.core.domain.UserPermissions;

public record LoginAndPermissionsGroup(Login login, UserPermissions userPermissions) {

}
