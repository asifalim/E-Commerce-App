package org.ecommerceapp.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserRole {
  CUSTOMER(1, "ROLE_CUSTOMER"),
  RETAILER(2, "ROLE_RETAILER"),
  ADMIN(3, "ROLE_ADMIN"),
  SUPERADMIN(4, "ROLE_SUPERADMIN");

  private final Integer hierarchyLevel;
  private final String authority;

  public boolean canManage(UserRole role) {
    return this.hierarchyLevel >= role.getHierarchyLevel();
  }

  public boolean isAdmin(){
    return this == ADMIN || this == SUPERADMIN;
  }

}