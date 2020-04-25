/**
 * 
 */
package za.co.sindi.cipc.rest.model;

import java.io.Serializable;
import java.util.Date;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import za.co.sindi.cipc.rest.adapter.DateAdapter;

/**
 * @author buhake.sindi
 * @since 2020/04/17
 *
 */
@XmlRootElement(name="enterprise-details")
@XmlAccessorType(XmlAccessType.FIELD)
public class EnterpriseDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7460508682731867711L;

	@XmlElement(name="enterprise-number")
	@JsonbProperty("enterprise-number")
	private String enterpriseNumber;
	
	@XmlElement(name="enterprise-name")
	@JsonbProperty("enterprise-name")
	private String enterpriseName;
	
	@XmlElement(name="enterprise-type")
	@JsonbProperty("enterprise-type")
	private String enterpriseType;
	
	@XmlElement(name="enterprise-status")
	@JsonbProperty("enterprise-status")
	private String enterpriseStatus;
	
	@XmlElement(name="compliance-notice-status")
	@JsonbProperty("compliance-notice-status")
	private String complianceNoticeStatus;
	
	@XmlElement(name="registration-date")
	@XmlJavaTypeAdapter(DateAdapter.class)
	@JsonbProperty("registration-date")
	@JsonbDateFormat(value = DateAdapter.DATE_FORMAT_PATTERN)
	private Date registrationDate;
	
	@XmlElement(name="physical-address")
	@JsonbProperty("physical-address")
	private String physicalAddress;
	
	@XmlElement(name="postal-address")
	@JsonbProperty("postal-address")
	private String postalAddress;

	/**
	 * 
	 */
	public EnterpriseDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param enterpriseNumber
	 * @param enterpriseName
	 * @param enterpriseType
	 * @param enterpriseStatus
	 * @param complianceNoticeStatus
	 * @param registrationDate
	 * @param physicalAddress
	 * @param postalAddress
	 */
	public EnterpriseDetails(String enterpriseNumber, String enterpriseName, String enterpriseType,	String enterpriseStatus, String complianceNoticeStatus, Date registrationDate, String physicalAddress, String postalAddress) {
		super();
		this.enterpriseNumber = enterpriseNumber;
		this.enterpriseName = enterpriseName;
		this.enterpriseType = enterpriseType;
		this.enterpriseStatus = enterpriseStatus;
		this.complianceNoticeStatus = complianceNoticeStatus;
		this.registrationDate = registrationDate;
		this.physicalAddress = physicalAddress;
		this.postalAddress = postalAddress;
	}

	/**
	 * @return the enterpriseNumber
	 */
	public String getEnterpriseNumber() {
		return enterpriseNumber;
	}

	/**
	 * @param enterpriseNumber the enterpriseNumber to set
	 */
	public void setEnterpriseNumber(String enterpriseNumber) {
		this.enterpriseNumber = enterpriseNumber;
	}

	/**
	 * @return the enterpriseName
	 */
	public String getEnterpriseName() {
		return enterpriseName;
	}

	/**
	 * @param enterpriseName the enterpriseName to set
	 */
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	/**
	 * @return the enterpriseType
	 */
	public String getEnterpriseType() {
		return enterpriseType;
	}

	/**
	 * @param enterpriseType the enterpriseType to set
	 */
	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	/**
	 * @return the enterpriseStatus
	 */
	public String getEnterpriseStatus() {
		return enterpriseStatus;
	}

	/**
	 * @param enterpriseStatus the enterpriseStatus to set
	 */
	public void setEnterpriseStatus(String enterpriseStatus) {
		this.enterpriseStatus = enterpriseStatus;
	}

	/**
	 * @return the complianceNoticeStatus
	 */
	public String getComplianceNoticeStatus() {
		return complianceNoticeStatus;
	}

	/**
	 * @param complianceNoticeStatus the complianceNoticeStatus to set
	 */
	public void setComplianceNoticeStatus(String complianceNoticeStatus) {
		this.complianceNoticeStatus = complianceNoticeStatus;
	}

	/**
	 * @return the registrationDate
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * @return the physicalAddress
	 */
	public String getPhysicalAddress() {
		return physicalAddress;
	}

	/**
	 * @param physicalAddress the physicalAddress to set
	 */
	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

	/**
	 * @return the postalAddress
	 */
	public String getPostalAddress() {
		return postalAddress;
	}

	/**
	 * @param postalAddress the postalAddress to set
	 */
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
}
