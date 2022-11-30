package com.ecommerce.Exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorDetails {

	
    private LocalDateTime timeStamp;
	private String message ;
	private String discription;

}
