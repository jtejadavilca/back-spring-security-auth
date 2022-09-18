package com.portfolio.jtvdev.springsecurity.domain.port.in;

import com.portfolio.jtvdev.springsecurity.adapter.in.model.RegisterResponse;
import com.portfolio.jtvdev.springsecurity.entity.RegisterEntity;

public interface RegisterUseCase {

  RegisterResponse register(RegisterEntity registerEntity) throws Exception;
}
