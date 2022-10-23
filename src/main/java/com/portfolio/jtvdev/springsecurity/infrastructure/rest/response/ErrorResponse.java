package com.portfolio.jtvdev.springsecurity.infrastructure.rest.response;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private int code;
  private String message;
}
