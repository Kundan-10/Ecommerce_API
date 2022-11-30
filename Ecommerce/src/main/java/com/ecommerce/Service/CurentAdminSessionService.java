package com.ecommerce.Service;

import com.ecommerce.Exception.LogInException;
import com.ecommerce.model.Login;

public interface CurentAdminSessionService {
	public String AdminlogInService (Login logdto) throws LogInException;
	public String AdminlogOutService (String Key) throws LogInException;

}
