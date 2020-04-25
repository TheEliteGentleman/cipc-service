/**
 * 
 */
package za.co.sindi.cipc.rest.service;

import za.co.sindi.cipc.rest.model.EnterpriseDetails;
import za.co.sindi.cipc.rest.model.EnterpriseNameSearchResult;

/**
 * @author buhake.sindi
 * @since 2020/04/17
 *
 */
public interface EnterpriseService {

	public EnterpriseDetails getEntepriseDetailsByEnterpriseNumber(final String enterpriseNumber);
	public EnterpriseNameSearchResult[] getEntepriseDetailsByEnterpriseName(final String enterpriseName);
}
