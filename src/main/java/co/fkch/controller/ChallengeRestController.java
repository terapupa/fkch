package co.fkch.controller;

import co.fkch.domain.Challenge;
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

    @RequestMapping(method = RequestMethod.GET)
    public List<Challenge> getAll() {
        return challengeService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Challenge create(@RequestBody Challenge challenge) {
        return challengeService.create(challenge);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable String id) {
        challengeService.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public Challenge update(@PathVariable String id, @RequestBody Challenge challenge) {
        return challengeService.update(id, challenge);
    }
}
