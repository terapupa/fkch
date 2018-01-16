package co.fkch;

import co.fkch.domain.Challenge;
import co.fkch.repository.ChallengeRepository;
import co.fkch.domain.ChallengeTag;
import co.fkch.repository.ChallengeTagRepository;
import co.fkch.domain.Company;
import co.fkch.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public static void main(String[] args) {
        SpringApplication.run(hello.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        companyRepository.deleteAll();
        challengeRepository.deleteAll();
        challengeTagRepository.deleteAll();
        // save a couple of companies
        Company amino = companyRepository.insert(new Company("Amino Payment"));
        companyRepository.insert(new Company("The MeetMe"));

        List<ChallengeTag> challengeTags = new ArrayList<>();
        challengeTags.add(new ChallengeTag("#tag1"));
        challengeTags.add(new ChallengeTag("#tag2"));
        challengeTags.add(new ChallengeTag("#tag3"));
        Challenge challenge = new Challenge();

        challenge.setCompanyName(amino.getCompanyName());
        challenge.setDescription("Amino Payment president election");
        challenge.setChallengeTags(challengeTags);
        challenge = challengeRepository.insert(challenge);

        List<Challenge> challenges = amino.getChallenges();

        if (challenges == null)
        {
            challenges = new ArrayList<>();
            amino.setChallenges(challenges);
        }
        challenges.add(challenge);
        companyRepository.save(amino);

        List<Challenge> challengeList = challengeRepository.findByCompanyName(amino.getCompanyName());

        List<String> p = new ArrayList<>();
        p.add("#tag31");
        p.add("#tag21");
        challengeList = challengeRepository.findByChallengeTagsIn(p);



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
