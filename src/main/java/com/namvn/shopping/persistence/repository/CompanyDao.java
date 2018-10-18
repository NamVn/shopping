package com.namvn.shopping.persistence.repository;

import com.namvn.shopping.pagination.PagingResult;
import com.namvn.shopping.persistence.entity.Company;

public interface CompanyDao {
    void add(Company company);
    void remove(Company company);
    void edit(Company company);
    PagingResult<Company> getCompanies();
    Company getCompany(Long companyId);
}
