package co.fkch.repository;

import co.fkch.domain.Challenge;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ChallengeRepository extends MongoRepository<Challenge, String> {
    public List<Challenge> findByCompanyId(String companyId);

    @Query("{ 'challengeTags.tag' : { $in : ?0} }")
    public List<Challenge> findByChallengeTagsIn(Collection<String> tags);

}
