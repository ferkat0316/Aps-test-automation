package com.aps.testing.assertionCommons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.aps.testing.commons.IdentityCollections;
import com.aps.testing.util.ValidationConstants;
import com.aps.testing.util.ValidationHelper;

@Component
public class CurrIdentityAssertionCommons {

	@Autowired
	IdentityCollections identityCollections;

	public void verifyFirstName(String expected) {
		Assert.isTrue(
				ValidationHelper.validateAssertions(expected, identityCollections.getCurrIdentity().getFirstName()),
				ValidationConstants.EQUALEXPRESSION.toString());
	}
	
	public void verifyLastName(String expected) {
		Assert.isTrue(
				ValidationHelper.validateAssertions(expected, identityCollections.getCurrIdentity().getLastName()),
				ValidationConstants.EQUALEXPRESSION.toString());
	}
}
