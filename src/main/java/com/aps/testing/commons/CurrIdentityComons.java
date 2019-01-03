package com.aps.testing.commons;

import org.springframework.beans.factory.annotation.Autowired;

import com.aps.testing.assertionCommons.CurrIdentityAssertionCommons;
import com.aps.testing.entity.CurrIdentity;

public class CurrIdentityComons {

	@Autowired
	CurrIdentityAssertionCommons currIdentityAssertion;

	@Autowired
	IdentityCollections identityCollections;

	public void verifyElements(String expectedFirstName, String expectedLastName) {
		for (CurrIdentity ci : identityCollections.getCurrIdentityList()) {
			if (ci.getFirstName().equals(expectedFirstName)) {
				identityCollections.setCurrIdentity(ci);
			}
		}
		currIdentityAssertion.verifyFirstName(expectedFirstName);
		currIdentityAssertion.verifyLastName(expectedLastName);
	}

}
