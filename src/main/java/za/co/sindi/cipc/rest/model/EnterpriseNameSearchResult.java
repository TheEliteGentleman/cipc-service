/**
 * 
 */
package za.co.sindi.cipc.rest.model;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author buhake.sindi
 * @since 2020/04/22
 *
 */
@XmlRootElement(name="enterprise-name-search-result")
@XmlAccessorType(XmlAccessType.FIELD)
public class EnterpriseNameSearchResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7697506161365326043L;
	
	@XmlElement(name="enterprise-name")
	@JsonbProperty("enterprise-name")
	private String enterpriseName;
	
	@XmlElement(name="enterprise-number")
	@JsonbProperty("enterprise-number")
	private String enterpriseTrackingNumber;
	
	@XmlElement(name="status")
	@JsonbProperty("status")
	private String status;
	
	/**
	 * 
	 */
	public EnterpriseNameSearchResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param enterpriseName
	 * @param enterpriseTrackingNumber
	 * @param status
	 */
	public EnterpriseNameSearchResult(String enterpriseName, String enterpriseTrackingNumber, String status) {
		super();
		this.enterpriseName = enterpriseName;
		this.enterpriseTrackingNumber = enterpriseTrackingNumber;
		this.status = status;
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
	 * @return the enterpriseTrackingNumber
	 */
	public String getEnterpriseTrackingNumber() {
		return enterpriseTrackingNumber;
	}
	
	/**
	 * @param enterpriseTrackingNumber the enterpriseTrackingNumber to set
	 */
	public void setEnterpriseTrackingNumber(String enterpriseTrackingNumber) {
		this.enterpriseTrackingNumber = enterpriseTrackingNumber;
	}
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
