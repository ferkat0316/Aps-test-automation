package com.aps.testing.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class CurrIdentity {

	private static final long serialVersionUID = 1L;
	private long identityId;
	private String actionFlag;
	private String activeInd;
	private Date alertDate;
	private String businessName;
	private String createdBy;
	private Date createdDate;
	private Date dateIncorporated;
	private Date dob;
	private Date dod;
	private String ein;
	private String entitySubType;
	private String entityType;
	private String firstName;
	private String gender;
	private String lastName;
	private String legalName;
	private BigDecimal medicareCount;
	private String medicareId;
	private String middleName;
	private String namePrefix;
	private String nameSuffix;
	private String npi;
	private BigDecimal npiCount;
	private String prvdrTypeCd;
	private String sourceCode;
	private String sourceId;
	private String ssn;
	private String batchId;
}
