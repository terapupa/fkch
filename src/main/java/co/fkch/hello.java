package co.fkch;

import co.fkch.domain.Challenge;
import co.fkch.domain.ChallengeTag;
import co.fkch.domain.Comment;
import co.fkch.domain.Company;
import co.fkch.repository.ChallengeRepository;
import co.fkch.repository.ChallengeTagRepository;
import co.fkch.repository.CommentRepository;
import co.fkch.repository.CompanyRepository;
import co.fkch.repository.UserRepository;
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


    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private ChallengeTagRepository challengeTagRepository;

    @Autowired
    private UserRepository userRepository;

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
        userRepository.deleteAll();
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
        System.out.println("Companies found with findAll():");
        System.out.println("-------------------------------");
        for (Company company : companyRepository.findAll()) {
            System.out.println(company);
        }
        System.out.println();

        // fetch an individual company
        System.out.println("Company found with findByCompanyName('Amino Payment'):");
        System.out.println("--------------------------------");
        System.out.println(companyRepository.findByCompanyName("Amino Payment"));

        System.out.println("Company found with findByCompanyName('The MeetMe'):");
        System.out.println("--------------------------------");
        System.out.println(companyRepository.findByCompanyName("The MeetMe"));
    }
}
