package co.fkch.controller;

import co.fkch.domain.Challenge;
import co.fkch.repository.ChallengeRepository;
import co.fkch.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/challenges")
public class ChallengeRestController {

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private CompanyRestController companyRestController;

    @RequestMapping(method = RequestMethod.GET)
    public List<Challenge> getAll() {
        return challengeRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Challenge create(@RequestBody Challenge challenge) {
        return challengeService.createChallenge(challenge);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable String id) {
        challengeRepository.delete(id);

    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public Challenge update(@PathVariable String id, @RequestBody Challenge challenge) {
        return challengeService.updateChallenge(id, challenge);
//
//
//        Challenge c = challengeRepository.findOne(id);
////        if (c.getCompany().getCompanyName().equalsIgnoreCase(challenge))
////        {
////
////        }
//        c.setCompany(challenge.getCompany());
//        return challengeRepository.save(c);
    }
}
