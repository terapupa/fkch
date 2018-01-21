package co.fkch.service;

import co.fkch.domain.Challenge;
import co.fkch.domain.ChallengeTag;
import co.fkch.domain.Company;
import co.fkch.exception.ResourceNotFoundException;
import co.fkch.repository.ChallengeRepository;
import co.fkch.repository.ChallengeTagRepository;
import co.fkch.repository.CompanyRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private ChallengeTagRepository challengeTagRepository;

    public Challenge createChallenge(Challenge challenge) {
        //Company
        Company company = challenge.getCompany();
        if (company == null) {
            throw new ResourceNotFoundException(null, "Company not found in the Challenge");
        }
        Company c = companyRepository.findByCompanyNameIgnoreCase(company.getCompanyName());
        if (c == null) {
            c = companyRepository.insert(company);
        }
        //Tags
        List<ChallengeTag> tags = challenge.getChallengeTags();
        if (CollectionUtils.isNotEmpty(tags)) {
            challenge.setChallengeTags(challengeTagRepository.save(tags));
        }
        return challengeRepository.insert(challenge);
    }

    public Challenge updateChallenge(String id, Challenge challenge) {
        Challenge c = challengeRepository.findOne(id);
//        if (c.getCompany().getCompanyName().equalsIgnoreCase(challenge))
//        {
//
//        }
        c.setCompany(challenge.getCompany());
        return challengeRepository.save(c);
    }
}
