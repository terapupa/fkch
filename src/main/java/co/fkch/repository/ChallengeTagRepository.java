package co.fkch.repository;

import co.fkch.domain.ChallengeTag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChallengeTagRepository extends MongoRepository<ChallengeTag, String> {
}
