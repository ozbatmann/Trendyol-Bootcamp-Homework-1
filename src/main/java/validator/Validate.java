package validator;

import exception.BlackListException;
import model.company.Company;

import java.util.List;

public interface Validate {

	public boolean validateBilling(Company company);

	public void validateInBlackList(String companyId, List<String> blackList) throws BlackListException;
}
