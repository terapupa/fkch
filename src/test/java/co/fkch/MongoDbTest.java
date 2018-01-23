package co.fkch;

import co.fkch.domain.Challenge;
import co.fkch.domain.Company;
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



}
