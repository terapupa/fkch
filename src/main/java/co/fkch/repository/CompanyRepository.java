package co.fkch.repository;

import co.fkch.domain.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {

    Company findByCompanyName(String companyName);

    Company findByCompanyNameIgnoreCase(String companyName);

}
