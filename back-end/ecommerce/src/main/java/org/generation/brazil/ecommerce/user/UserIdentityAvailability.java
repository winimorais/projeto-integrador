package org.generation.brazil.ecommerce.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserIdentityAvailability {

  private Boolean available;

  public UserIdentityAvailability(Boolean available) {
    this.available = available;
  }

  public Boolean getAvailable() {
    return available;
  }

  public void setAvailable(Boolean available) {
    this.available = available;
  }
}
