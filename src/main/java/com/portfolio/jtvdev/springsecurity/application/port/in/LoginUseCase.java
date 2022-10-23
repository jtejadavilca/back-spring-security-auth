package com.portfolio.jtvdev.springsecurity.application.port.in;

import com.portfolio.jtvdev.springsecurity.infrastructure.rest.response.LoginResponse;
import com.portfolio.jtvdev.springsecurity.domain.model.LoginModel;

public interface LoginUseCase {
  LoginResponse login(LoginModel loginEntity) throws Exception;
}
