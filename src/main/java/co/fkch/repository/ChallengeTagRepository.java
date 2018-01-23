package co.fkch.repository;

import co.fkch.domain.ChallengeTag;
import co.fkch.domain.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeTagRepository extends MongoRepository<ChallengeTag, String> {
    ChallengeTag findByTag(String tag);
}
