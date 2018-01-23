package co.fkch;

import co.fkch.domain.Challenge;
import co.fkch.domain.Comment;
import co.fkch.domain.Company;
import co.fkch.domain.Solution;
import co.fkch.exception.ResourceNotFoundException;
import co.fkch.repository.ChallengeRepository;
import co.fkch.repository.ChallengeTagRepository;
import co.fkch.repository.CompanyRepository;
import co.fkch.service.ChallengeService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDbTest {

    @Autowired
    ChallengeRepository challengeRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ChallengeTagRepository challengeTagRepository;
    @Autowired
    ChallengeService challengeService;

    @Before
    public void init() {
        challengeRepository.deleteAll();
        companyRepository.deleteAll();
        challengeTagRepository.deleteAll();
    }

    @Test
    public void createChallenge() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany(new Company("Company_1"));
        Challenge db = challengeService.create(challenge);
        Company c = companyRepository.findByCompanyName("Company_1");
        assertTrue(db.getDescription().equals(challenge.getDescription()));
        assertTrue(db.getCompany().getCompanyName().equals(challenge.getCompany().getCompanyName()));
        assertTrue(StringUtils.isNotEmpty(db.getId()));
        assertTrue(StringUtils.isNotEmpty(db.getCompany().getId()));
        assertTrue(c!= null);
        assertTrue(c.getCompanyName().equals("Company_1"));
        assertTrue(c.getId().equals(db.getCompany().getId()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void createChallengeEmptyCompany() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challengeService.create(challenge);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void createChallengeEmptyCompanyName() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany(new Company());
        challengeService.create(challenge);
    }

    @Test
    public void deleteChallenge() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany(new Company("Company_1"));
        Challenge db = challengeService.create(challenge);
        challengeService.delete(db.getId());
        Company c = companyRepository.findByCompanyName("Company_1");
        assertFalse(challengeRepository.exists(db.getId()));
        assertTrue(c!=null);
    }

    @Test
    public void updateChallenge() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany(new Company("Company_1"));
        Challenge db = challengeService.create(challenge);
        Challenge challenge1 = new Challenge();
        challenge1.setDescription("Challenge_Update");
        Challenge db1 = challengeService.update(db.getId(), challenge1);
        Challenge db2 = challengeRepository.findOne(db.getId());
        assertTrue(db2.getId().equals(db1.getId()));
        assertTrue("Challenge_Update".equals(db2.getDescription()));
    }

    @Test
    public void addComment() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany(new Company("Company_1"));
        Challenge db = challengeService.create(challenge);
        db = challengeService.addComment(db.getId(), new Comment("Comment_1"));
        assertTrue("Comment_1".equals(db.getComments().get(0).getCommentBody()));
    }

    @Test
    public void deleteComment() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany(new Company("Company_1"));
        Challenge db = challengeService.create(challenge);
        db = challengeService.addComment(db.getId(), new Comment("Comment_1"));
        db = challengeService.deleteComment(db.getId(), db.getComments().get(0).getId());
        assertTrue(db.getComments() == null || db.getComments().isEmpty());
        Challenge db1 = challengeRepository.findOne(db.getId());
        assertTrue(db1.getComments() == null || db1.getComments().isEmpty());
    }

    @Test
    public void updateComment() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany(new Company("Company_1"));
        Challenge db = challengeService.create(challenge);
        db = challengeService.addComment(db.getId(), new Comment("Comment_1"));
        db = challengeService.updateComment(db.getId(), db.getComments().get(0).getId(),
                new Comment("new_Comment"));
        assertTrue("new_Comment".equals(db.getComments().get(0).getCommentBody()));
        Challenge db1 = challengeRepository.findOne(db.getId());
        assertTrue("new_Comment".equals(db1.getComments().get(0).getCommentBody()));
    }

    @Test
    public void addSolution() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany(new Company("Company_1"));
        Challenge db = challengeService.create(challenge);
        db = challengeService.addSolution(db.getId(),
                new Solution("Solution_1", "language_1"));
        assertTrue("Solution_1".equals(db.getSolutions().get(0).getSolutionBody()));
        assertTrue("language_1".equals(db.getSolutions().get(0).getLanguage()));
        Challenge db1 = challengeRepository.findOne(db.getId());
        assertTrue("Solution_1".equals(db1.getSolutions().get(0).getSolutionBody()));
        assertTrue("language_1".equals(db1.getSolutions().get(0).getLanguage()));
        db = challengeService.addSolution(db.getId(),
                new Solution("Solution_2", "language_2"));
        assertTrue("Solution_2".equals(db.getSolutions().get(1).getSolutionBody()));
        assertTrue("language_2".equals(db.getSolutions().get(1).getLanguage()));
        db1 = challengeRepository.findOne(db.getId());
        assertTrue("Solution_2".equals(db1.getSolutions().get(1).getSolutionBody()));
        assertTrue("language_2".equals(db1.getSolutions().get(1).getLanguage()));
    }

    @Test
    public void deleteSolution() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany(new Company("Company_1"));
        Challenge db = challengeService.create(challenge);
        db = challengeService.addSolution(db.getId(), new Solution("Solution_1", "language_1"));
        db = challengeService.deleteSolution(db.getId(), db.getSolutions().get(0).getId());
        assertTrue(db.getSolutions() == null || db.getSolutions().isEmpty());
        Challenge db1 = challengeRepository.findOne(db.getId());
        assertTrue(db1.getSolutions() == null || db1.getSolutions().isEmpty());
    }

    @Test
    public void updateSolution() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany(new Company("Company_1"));
        Challenge db = challengeService.create(challenge);
        db = challengeService.addSolution(db.getId(), new Solution("Solution_1", "language_1"));
        db = challengeService.updateSolution(db.getId(), db.getSolutions().get(0).getId(),
                new Solution("new_Solution", "new_language"));
        assertTrue("new_Solution".equals(db.getSolutions().get(0).getSolutionBody()) &&
                "new_language".equals(db.getSolutions().get(0).getLanguage()));
        Challenge db1 = challengeRepository.findOne(db.getId());
        assertTrue("new_Solution".equals(db1.getSolutions().get(0).getSolutionBody()) &&
                "new_language".equals(db1.getSolutions().get(0).getLanguage()));
    }

    //todo - change 3 below method to proper implementation
    @Test
    public void addCommentToSolutuin() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany(new Company("Company_1"));
        Challenge db = challengeService.create(challenge);
        db = challengeService.addComment(db.getId(), new Comment("Comment_1"));
        assertTrue("Comment_1".equals(db.getComments().get(0).getCommentBody()));
    }

    @Test
    public void deleteCommentFromSolution() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany(new Company("Company_1"));
        Challenge db = challengeService.create(challenge);
        db = challengeService.addComment(db.getId(), new Comment("Comment_1"));
        db = challengeService.deleteComment(db.getId(), db.getComments().get(0).getId());
        assertTrue(db.getComments() == null || db.getComments().isEmpty());
        Challenge db1 = challengeRepository.findOne(db.getId());
        assertTrue(db1.getComments() == null || db1.getComments().isEmpty());
    }

    @Test
    public void updateCommentInSolution() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany(new Company("Company_1"));
        Challenge db = challengeService.create(challenge);
        db = challengeService.addComment(db.getId(), new Comment("Comment_1"));
        db = challengeService.updateComment(db.getId(), db.getComments().get(0).getId(),
                new Comment("new_Comment"));
        assertTrue("new_Comment".equals(db.getComments().get(0).getCommentBody()));
        Challenge db1 = challengeRepository.findOne(db.getId());
        assertTrue("new_Comment".equals(db1.getComments().get(0).getCommentBody()));
    }

}
