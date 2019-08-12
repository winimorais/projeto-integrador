package org.generation.brazil.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BackendException extends RuntimeException {

  public BackendException(String message) {
    super(message);
  }

}
