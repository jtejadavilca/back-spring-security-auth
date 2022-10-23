package com.portfolio.jtvdev.springsecurity.application.port.in;

import com.portfolio.jtvdev.springsecurity.infrastructure.rest.response.RegisterResponse;
import com.portfolio.jtvdev.springsecurity.domain.model.RegisterModel;

public interface RegisterUseCase {

  RegisterResponse register(RegisterModel registerEntity) throws Exception;
}
