package co.fkch.repository;

import co.fkch.domain.Solution;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SolutionRepository extends MongoRepository<Solution, String> {
}
