package com.aps.testing.commons;

import java.util.List;

import org.springframework.stereotype.Component;

import com.aps.testing.entity.CurrIdentity;

import lombok.Data;

@Data
@Component
public class IdentityCollections {
	private List<CurrIdentity> currIdentityList=null;
	private CurrIdentity currIdentity=null;
	
	
}
