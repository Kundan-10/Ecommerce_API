package com.ecommerce.Service;

import com.ecommerce.Exception.LogInException;
import com.ecommerce.model.Login;

public interface CurrentUserSessionService {

    public String UserlogInService (Login logdto) throws LogInException;
	public String UserlogOutService (String Key) throws LogInException;
	
	
}
