package co.fkch;

import co.fkch.domain.Company;
import co.fkch.domain.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class hello implements CommandLineRunner {

    @Autowired
    private CompanyRepository companyRepository;

    public static void main(String[] args) {
        SpringApplication.run(hello.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        companyRepository.deleteAll();
        // save a couple of companies
        companyRepository.save(new Company("Amino Payment"));
        companyRepository.save(new Company("The MeetMe"));

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
