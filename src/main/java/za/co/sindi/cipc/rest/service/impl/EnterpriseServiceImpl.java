/**
 * 
 */
package za.co.sindi.cipc.rest.service.impl;

import javax.ejb.Stateless;

import za.co.sindi.cipc.rest.model.EnterpriseDetails;
import za.co.sindi.cipc.rest.model.EnterpriseNameSearchResult;
import za.co.sindi.cipc.rest.service.EnterpriseService;
import za.co.sindi.cipc.rest.service.utils.WebBrowserUtils;

/**
 * @author buhake.sindi
 * @since 202/04/17
 *
 */
@Stateless
public class EnterpriseServiceImpl implements EnterpriseService {

	@Override
	public EnterpriseDetails getEntepriseDetailsByEnterpriseNumber(String enterpriseNumber) {
		// TODO Auto-generated method stub
		return WebBrowserUtils.getCipcEnterpriseDetailsByEnterpriseNumber(enterpriseNumber);
	}

	@Override
	public EnterpriseNameSearchResult[] getEntepriseDetailsByEnterpriseName(String enterpriseName) {
		// TODO Auto-generated method stub
		return WebBrowserUtils.getCipcEnterpriseDetailsByEnterpriseName(enterpriseName);
	}
}
