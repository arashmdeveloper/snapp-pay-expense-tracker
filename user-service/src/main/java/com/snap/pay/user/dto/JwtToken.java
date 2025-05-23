package com.snap.pay.user.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtToken {
	
	private String token;
	private Date issueAt;
	private String subject;
	private Date expireIn;

}
