package co.fkch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import co.fkch.domain.Challenge;
import co.fkch.domain.ChallengeTag;
import co.fkch.domain.Comment;
import co.fkch.domain.Company;
import co.fkch.domain.Solution;
import co.fkch.exception.AttributeNotDefinedException;
import co.fkch.repository.ChallengeRepository;
import co.fkch.repository.ChallengeTagRepository;
import co.fkch.repository.CompanyRepository;
import co.fkch.service.ChallengeService;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDbTest {

    @Autowired
    private
    ChallengeRepository challengeRepository;
    @Autowired
    private
    CompanyRepository companyRepository;
    @Autowired
    private
    ChallengeTagRepository challengeTagRepository;
    @Autowired
    private
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
        challenge.setCompany("Company_1");
        Challenge db = challengeService.create(challenge);
        Company c = companyRepository.findByCompanyName("Company_1");
        assertEquals(db.getDescription(), challenge.getDescription());
        assertEquals(db.getCompany(), challenge.getCompany());
        assertTrue(StringUtils.isNotEmpty(db.getId()));
        assertNotNull(c);
        assertEquals("Company_1", c.getCompanyName());
    }

    @Test
    public void createChallengeTwice() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany("Company_1");
        challenge.setChallengeTags(Arrays.asList(new ChallengeTag("#tag1"), new ChallengeTag("#tag2")));
        Challenge db = challengeService.create(challenge);
        Company c = companyRepository.findByCompanyName("Company_1");
        assertEquals(db.getDescription(), challenge.getDescription());
        assertEquals(db.getCompany(), challenge.getCompany());
        assertTrue(StringUtils.isNotEmpty(db.getId()));
        assertNotNull(c);
        assertEquals("Company_1", c.getCompanyName());

        challenge = new Challenge();
        challenge.setDescription("Challenge_2");
        challenge.setCompany("Company_1");
        challenge.setChallengeTags(Arrays.asList(new ChallengeTag("#tag1"), new ChallengeTag("#tag2")));
        db = challengeService.create(challenge);
        c = companyRepository.findByCompanyName("Company_1");
        assertEquals(db.getDescription(), challenge.getDescription());
        assertEquals(db.getCompany(), challenge.getCompany());
        assertTrue(StringUtils.isNotEmpty(db.getId()));
        assertNotNull(c);
        assertEquals("Company_1", c.getCompanyName());

    }


    @Test(expected = AttributeNotDefinedException.class)
    public void createChallengeEmptyCompanyName() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challengeService.create(challenge);
    }

    @Test
    public void deleteChallenge() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany("Company_1");
        Challenge db = challengeService.create(challenge);
        challengeService.delete(db.getId());
        Company c = companyRepository.findByCompanyName("Company_1");
        assertFalse(challengeRepository.exists(db.getId()));
        assertNotNull(c);
    }

    @Test
    public void updateChallenge() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany("Company_1");
        Challenge db = challengeService.create(challenge);
        Challenge challenge1 = new Challenge();
        challenge1.setDescription("Challenge_Update");
        Challenge db1 = challengeService.update(db.getId(), challenge1);
        Challenge db2 = challengeRepository.findOne(db.getId());
        assertEquals(db2.getId(), db1.getId());
        assertEquals("Challenge_Update", db2.getDescription());
    }

    @Test
    public void addComment() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany("Company_1");
        Challenge db = challengeService.create(challenge);
        db = challengeService.addComment(db.getId(), new Comment("Comment_1"));
        assertEquals("Comment_1", db.getComments().get(0).getCommentBody());
    }

    @Test
    public void deleteComment() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany("Company_1");
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
        challenge.setCompany("Company_1");
        Challenge db = challengeService.create(challenge);
        db = challengeService.addComment(db.getId(), new Comment("Comment_1"));
        db = challengeService.updateComment(db.getId(), db.getComments().get(0).getId(),
                new Comment("new_Comment"));
        assertEquals("new_Comment", db.getComments().get(0).getCommentBody());
        Challenge db1 = challengeRepository.findOne(db.getId());
        assertEquals("new_Comment", db1.getComments().get(0).getCommentBody());
    }

    @Test
    public void addSolution() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany("Company_1");
        Challenge db = challengeService.create(challenge);
        db = challengeService.addSolution(db.getId(),
                new Solution("Solution_1", "language_1"));
        assertEquals("Solution_1", db.getSolutions().get(0).getSolutionBody());
        assertEquals("language_1", db.getSolutions().get(0).getLanguage());
        Challenge db1 = challengeRepository.findOne(db.getId());
        assertEquals("Solution_1", db1.getSolutions().get(0).getSolutionBody());
        assertEquals("language_1", db1.getSolutions().get(0).getLanguage());
        db = challengeService.addSolution(db.getId(),
                new Solution("Solution_2", "language_2"));
        assertEquals("Solution_2", db.getSolutions().get(1).getSolutionBody());
        assertEquals("language_2", db.getSolutions().get(1).getLanguage());
        db1 = challengeRepository.findOne(db.getId());
        assertEquals("Solution_2", db1.getSolutions().get(1).getSolutionBody());
        assertEquals("language_2", db1.getSolutions().get(1).getLanguage());
    }

    @Test
    public void deleteSolution() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany("Company_1");
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
        challenge.setCompany("Company_1");
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

    @Test
    public void addCommentToSolution() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany("Company_1");
        challenge.setSolutions(new ArrayList<>());
        challenge.getSolutions().add(new Solution("Solution_1", "language_1"));
        Challenge db = challengeService.create(challenge);
        db = challengeService.addCommentToSolution(db.getId(), db.getSolutions().get(0).getId(), new Comment("Comment_1"));
        assertEquals("Comment_1", db.getSolutions().get(0).getComments().get(0).getCommentBody());
    }

    @Test
    public void deleteCommentFromSolution() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany("Company_1");
        challenge.setSolutions(new ArrayList<>());
        Solution solution = new Solution("Solution_1", "language_1");
        solution.setComments(new ArrayList<>());
        solution.getComments().add(new Comment("Comment_1"));
        challenge.getSolutions().add(solution);
        Challenge db = challengeService.create(challenge);

        db = challengeService.deleteCommentFromSolution(db.getId(), db.getSolutions().get(0).getId(),
                db.getSolutions().get(0).getComments().get(0).getId());
        assertTrue(db.getSolutions().get(0).getComments() == null || db.getSolutions().get(0).getComments().isEmpty());
    }

    @Test
    public void updateCommentInSolution() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Challenge_1");
        challenge.setCompany("Company_1");
        challenge.setSolutions(new ArrayList<>());
        Solution solution = new Solution("Solution_1", "language_1");
        solution.setComments(new ArrayList<>());
        solution.getComments().add(new Comment("Comment_1"));
        challenge.getSolutions().add(solution);
        Challenge db = challengeService.create(challenge);
        db = challengeService.updateCommentInSolution(db.getId(), db.getSolutions().get(0).getId(),
                db.getSolutions().get(0).getComments().get(0).getId(), new Comment("new_Comment"));
        assertEquals("new_Comment", db.getSolutions().get(0).getComments().get(0).getCommentBody());
        Challenge db1 = challengeRepository.findOne(db.getId());
        assertEquals("new_Comment", db1.getSolutions().get(0).getComments().get(0).getCommentBody());
    }

}
