package co.fkch.repository;

import co.fkch.domain.Challenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ChallengeRepository extends MongoRepository<Challenge, String> {

    List<Challenge> findByCompanyIgnoreCase(String companyName);

    Page<Challenge> findByCompanyIgnoreCase(String companyName, Pageable pageable);

//    @Query("{ 'challengeTags.tag' : { $in : ?0} }")
//    List<Challenge> findByChallengeTagsIn(Collection<String> tags);

    List<Challenge> findByChallengeTags_TagIn(Collection<String> tags);

    Page<Challenge> findByChallengeTags_TagIn(Collection<String> tags, Pageable pageable);

}
