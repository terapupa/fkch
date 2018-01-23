package co.fkch.service;

import co.fkch.domain.Challenge;
import co.fkch.domain.ChallengeTag;
import co.fkch.domain.Comment;
import co.fkch.domain.Company;
import co.fkch.domain.Solution;
import co.fkch.exception.ResourceNotFoundException;
import co.fkch.repository.ChallengeRepository;
import co.fkch.repository.ChallengeTagRepository;
import co.fkch.repository.CommentRepository;
import co.fkch.repository.CompanyRepository;
import co.fkch.repository.SolutionRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChallengeService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private ChallengeTagRepository challengeTagRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private SolutionRepository solutionRepository;

    public Challenge create(Challenge challenge) {
        //Company
        Company company = challenge.getCompany();
        if (company == null || StringUtils.isEmpty(company.getCompanyName())) {
            throw new ResourceNotFoundException(null, "Company not found in the Challenge");
        }
        Company c = companyRepository.findByCompanyNameIgnoreCase(company.getCompanyName());
        if (c == null) {
            c = companyRepository.insert(company);
        }
        challenge.setCompany(c);
        //Tags
        List<ChallengeTag> tags = challenge.getChallengeTags();
        if (CollectionUtils.isNotEmpty(tags)) {
            challenge.setChallengeTags(challengeTagRepository.save(tags));
        }
        return challengeRepository.insert(challenge);
    }

    public Challenge update(String challengeId, Challenge challenge) {
        Challenge dbChallenge = getChallengeById(challengeId);
        //Company
        Company company = challenge.getCompany();
        if (company != null && StringUtils.isNotEmpty(company.getCompanyName()))
        {
            Company comp = companyRepository.findByCompanyNameIgnoreCase(company.getCompanyName());
            if (comp == null) {
                comp = companyRepository.insert(company);
            }
            dbChallenge.setCompany(comp);
        }
        //Tags
        List<ChallengeTag> tags = challenge.getChallengeTags();
        if (CollectionUtils.isNotEmpty(tags)) {
            challenge.setChallengeTags(challengeTagRepository.save(tags));
        }
        dbChallenge.setChallengeTags(tags);
        dbChallenge.setComments(challenge.getComments());
        dbChallenge.setDescription(challenge.getDescription());
        dbChallenge.setSolutions(challenge.getSolutions());
        return challengeRepository.save(dbChallenge);
    }

    public List<Challenge> getAll() {
        return challengeRepository.findAll();
    }

    public void delete(String id) {
        challengeRepository.delete(id);
    }

    public Challenge addComment(String challengeId, Comment comment) {
        Challenge dbChallenge = getChallengeById(challengeId);
        if (StringUtils.isEmpty(comment.getId())) {
            comment.setId(new ObjectId().toString());
        }
        List<Comment> comments = dbChallenge.getComments();
        if (comments == null) {
            comments = new ArrayList<>();
            dbChallenge.setComments(comments);
        }
        comments.add(comment);
        return challengeRepository.save(dbChallenge);
    }

    public Challenge deleteComment(String challengeId, String commentId) {
        Challenge dbChallenge = getChallengeById(challengeId);
        Comment found = null;
        List<Comment> comments = dbChallenge.getComments();
        if (CollectionUtils.isNotEmpty(comments)) {
            for (Comment c : comments) {
                if (c.getId().equals(commentId)) {
                    found = c;
                    break;
                }
            }
            if (found != null) {
                dbChallenge.getComments().remove(found);
                dbChallenge = challengeRepository.save(dbChallenge);
            }
        }
        return dbChallenge;
    }

    public Challenge updateComment(String challengeId, String commentId, Comment comment) {
        Challenge dbChallenge = getChallengeById(challengeId);
        List<Comment> comments = dbChallenge.getComments();
        if (CollectionUtils.isNotEmpty(comments)) {
            for (Comment c : comments) {
                if (c.getId().equals(commentId)) {
                    c.setCommentBody(comment.getCommentBody());
                    break;
                }
            }
            dbChallenge = challengeRepository.save(dbChallenge);
        }
        return dbChallenge;
    }

    public Challenge addSolution(String challengeId, Solution solution) {
        Challenge dbChallenge = getChallengeById(challengeId);
        if (StringUtils.isEmpty(solution.getId())) {
            solution.setId(new ObjectId().toString());
        }
        List<Solution> solutions = dbChallenge.getSolutions();
        if (solutions == null) {
            solutions = new ArrayList<>();
            dbChallenge.setSolutions(solutions);
        }
        solutions.add(solution);
        return challengeRepository.save(dbChallenge);
    }

    public Challenge deleteSolution(String challengeId, String solutionId) {
        Challenge dbChallenge = getChallengeById(challengeId);
        Solution found = null;
        List<Solution> solutions = dbChallenge.getSolutions();
        if (CollectionUtils.isNotEmpty(solutions)) {
            for (Solution s : solutions) {
                if (s.getId().equals(solutionId)) {
                    found = s;
                    break;
                }
            }
            if (found != null) {
                dbChallenge.getSolutions().remove(found);
                dbChallenge = challengeRepository.save(dbChallenge);
            }
        }
        return dbChallenge;
    }

    public Challenge updateSolution(String challengeId, String solutionId, Solution solution) {
        Challenge dbChallenge = getChallengeById(challengeId);
        List<Solution> solutions = dbChallenge.getSolutions();
        if (CollectionUtils.isNotEmpty(solutions)) {
            for (Solution s : solutions) {
                if (s.getId().equals(solutionId)) {
                    s.setSolutionBody(solution.getSolutionBody());
                    s.setLanguage(solution.getLanguage());
                    break;
                }
            }
            dbChallenge = challengeRepository.save(dbChallenge);
        }
        return dbChallenge;
    }

    public Challenge addCommentToSolution(String challengeId, String solutionId, Comment comment) {
        Challenge dbChallenge = getChallengeById(challengeId);
        List<Solution> solutions = dbChallenge.getSolutions();
        if (CollectionUtils.isNotEmpty(solutions)) {
            for (Solution solution : dbChallenge.getSolutions()) {
                if (solution.getId().equals(solutionId)) {
                    List<Comment> comments = solution.getComments();
                    if (comments == null) {
                        comments = new ArrayList<>();
                        solution.setComments(comments);
                    }
                    if (StringUtils.isEmpty(comment.getId())) {
                        comment.setId(new ObjectId().toString());
                    }
                    comments.add(comment);
                    dbChallenge = challengeRepository.save(dbChallenge);
                    break;
                }
            }
        }
        return dbChallenge;
    }

    public Challenge deleteCommentFromSolution(String challengeId, String solutionId, String commentId) {
        Challenge dbChallenge = getChallengeById(challengeId);
        List<Solution> solutions = dbChallenge.getSolutions();
        if (CollectionUtils.isNotEmpty(solutions)) {
            for (Solution solution : dbChallenge.getSolutions()) {
                if (solution.getId().equals(solutionId)) {
                    List<Comment> comments = solution.getComments();
                    if (CollectionUtils.isNotEmpty(comments)) {
                        Comment found = null;
                        for (Comment comment : comments) {
                            if (comment.getId().equals(commentId)) {
                                found = comment;
                                break;
                            }
                        }
                        if (found != null) {
                            comments.remove(found);
                            dbChallenge = challengeRepository.save(dbChallenge);
                        }
                    }
                }
            }
        }
        return dbChallenge;
    }

    public Challenge updateCommentInSolution(String challengeId, String solutionId, String commentId, Comment comment) {
        Challenge dbChallenge = getChallengeById(challengeId);
        List<Solution> solutions = dbChallenge.getSolutions();
        if (CollectionUtils.isNotEmpty(solutions)) {
            for (Solution solution : dbChallenge.getSolutions()) {
                if (solution.getId().equals(solutionId)) {
                    List<Comment> comments = solution.getComments();
                    if (CollectionUtils.isNotEmpty(comments)) {
                        for (Comment c : comments) {
                            if (c.getId().equals(commentId)) {
                                c.setCommentBody(comment.getCommentBody());
                                dbChallenge = challengeRepository.save(dbChallenge);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return dbChallenge;
    }

    private Challenge getChallengeById(String challengeId) {
        Challenge dbChallenge = challengeRepository.findOne(challengeId);
        if (dbChallenge == null) {
            throw new ResourceNotFoundException(challengeId, "Challenge not found");
        }
        return dbChallenge;
    }

}
