package com.portfolio.jtvdev.springsecurity.adapter.in.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
public class Response<T> {

  private int status;
  private T data;
  private ErrorResponse error;
  public Response() {
  }
}
