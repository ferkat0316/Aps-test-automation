package com.aps.testing.util;


public enum ValidationConstants {


	EQUALEXPRESSION("The two values must be equal"),
	NOTNULLEXPRESSION("The value must not be null"),
	NULLEXPRESSION("The value must be null"),
	NOTMATCH("No record matches assertion"),
	NOTEMPTYEXPRESSION("The collection must have at least one element"),
	NOJSONFILECREATION("JSON File Should be created");

	private final String expression;

	private ValidationConstants(final String expression){
		this.expression = expression;
	}

	public String getExpression() {
		return expression;
	}

	@Override
	public String toString(){
		return expression;
	}


}
