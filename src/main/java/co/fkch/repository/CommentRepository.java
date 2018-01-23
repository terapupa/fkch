package co.fkch.repository;

import co.fkch.domain.Comment;
import co.fkch.domain.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
