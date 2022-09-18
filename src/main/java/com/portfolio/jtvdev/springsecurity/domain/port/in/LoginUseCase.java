package com.portfolio.jtvdev.springsecurity.domain.port.in;

import com.portfolio.jtvdev.springsecurity.adapter.in.model.LoginResponse;
import com.portfolio.jtvdev.springsecurity.entity.LoginEntity;

public interface LoginUseCase {
  LoginResponse login(LoginEntity loginEntity) throws Exception;
}
