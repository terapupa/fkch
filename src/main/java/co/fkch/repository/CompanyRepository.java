package co.fkch.repository;

import co.fkch.domain.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {
    public Company findByCompanyName(String companyName);
}
