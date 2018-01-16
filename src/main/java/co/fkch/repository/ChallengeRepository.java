package co.fkch.repository;

import co.fkch.domain.Challenge;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ChallengeRepository extends MongoRepository<Challenge, String> {

    List<Challenge> findByCompanyName(String companyName);

    @Query("{ 'challengeTags.tag' : { $in : ?0} }")
    List<Challenge> findByChallengeTagsIn(Collection<String> tags);

}
