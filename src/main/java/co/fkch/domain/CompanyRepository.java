package co.fkch.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {
    public Company findByCompanyName(String companyName);
}
