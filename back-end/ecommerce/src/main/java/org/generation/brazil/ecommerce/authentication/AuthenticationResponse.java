package org.generation.brazil.ecommerce.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AuthenticationResponse {

  private Boolean success;
  private String message;

  public AuthenticationResponse(Boolean success, String message) {
    this.success = success;
    this.message = message;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
