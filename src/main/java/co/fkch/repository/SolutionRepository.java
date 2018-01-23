package co.fkch.repository;

import co.fkch.domain.Comment;
import co.fkch.domain.Solution;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SolutionRepository extends MongoRepository<Solution, String> {
}
