package co.fkch;

import co.fkch.domain.Challenge;
import co.fkch.domain.ChallengeTag;
import co.fkch.domain.Comment;
import co.fkch.domain.Company;
import co.fkch.repository.ChallengeRepository;
import co.fkch.repository.ChallengeTagRepository;
import co.fkch.repository.CommentRepository;
import co.fkch.repository.CompanyRepository;
import co.fkch.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
public class hello implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(CommandLineRunner.class);


    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private ChallengeTagRepository challengeTagRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CommentRepository commentRepository;


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(hello.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        companyRepository.deleteAll();
        challengeRepository.deleteAll();
        challengeTagRepository.deleteAll();
        accountRepository.deleteAll();
        commentRepository.deleteAll();

        // save a couple of companies
        Company amino = companyRepository.insert(new Company("Amino Payment"));
        companyRepository.insert(new Company("The MeetMe"));


        Challenge challenge = new Challenge();
        List<ChallengeTag> challengeTags = new ArrayList<>();
        challengeTags.add(new ChallengeTag("#tag1"));
        challengeTags.add(new ChallengeTag("#tag2"));
        challengeTags.add(new ChallengeTag("#tag3"));

        challenge.setCompany(amino.getCompanyName());
        challenge.setDescription("Amino Payment president election");
        challenge.setChallengeTags(challengeTags);

        List<Comment> comments = new ArrayList<>();
        Comment comment1 = new Comment("Comment1");
        Comment comment2 = new Comment("Comment2");
        Comment comment3 = new Comment("Comment3");
        comments.add(comment1);
        comments.add(comment2);
        comments.add(comment3);
        challenge.setComments(comments);
        challengeRepository.insert(challenge);




        companyRepository.save(amino);

        List<Challenge> challengeList = challengeRepository.findByCompanyIgnoreCase("amIno Payment");

        List<String> p = new ArrayList<>();
        p.add("#tag1");
        p.add("#tag21");
        challengeList = challengeRepository.findByChallengeTags_TagIn(p);



        // fetch all companies
        logger.info("Companies found with findAll():");
        logger.info("-------------------------------");
        for (Company company : companyRepository.findAll()) {
            logger.info(company.toString());
        }

        // fetch an individual company
        logger.info("Company found with findByCompanyName('Amino Payment'):");
        logger.info("--------------------------------");
        logger.info(companyRepository.findByCompanyName("Amino Payment").toString());

        logger.info("Company found with findByCompanyName('The MeetMe'):");
        logger.info("--------------------------------");
        logger.info(companyRepository.findByCompanyName("The MeetMe").toString());
    }
}
