package co.fkch.repository;

import co.fkch.domain.ChallengeTag;
import co.fkch.domain.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChallengeTagRepository extends MongoRepository<ChallengeTag, String> {
    ChallengeTag findByTag(String tag);
}
