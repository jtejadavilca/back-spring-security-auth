package com.portfolio.jtvdev.springsecurity.adapter.in.model;

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
  private static final long serialVersionUID = 1L;

  private int code;
  private String message;
}
