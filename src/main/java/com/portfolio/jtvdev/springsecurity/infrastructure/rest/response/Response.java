package com.portfolio.jtvdev.springsecurity.infrastructure.rest.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class Response<T> {

  private int status;
  private T data;
  private ErrorResponse error;
  public Response() {
  }
}
