package co.fkch.repository;

import co.fkch.domain.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {
    Company findByCompanyName(String companyName);
}
