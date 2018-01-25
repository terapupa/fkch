package co.fkch.controller;

import co.fkch.domain.Challenge;
import co.fkch.domain.Comment;
import co.fkch.domain.Solution;
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

    @RequestMapping(method = RequestMethod.GET, value = "company/{companyName}")
    public List<Challenge> getByCompanyName(@PathVariable String companyName) {
        return challengeService.getByCompanyName(companyName);
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

    @RequestMapping(method = RequestMethod.POST, value = "{id}/comment")
    public Challenge addComment(@PathVariable String id, @RequestBody Comment comment) {
        return challengeService.addComment(id, comment);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}/comment/{commentId}")
    public void deleteComment(@PathVariable String id, @PathVariable String commentId) {
        challengeService.deleteComment(id, commentId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}/comment/{commentId}")
    public Challenge updateComment(@PathVariable String id, @PathVariable String commentId,  @RequestBody Comment comment) {
        return challengeService.updateComment(id, commentId, comment);
    }

    @RequestMapping(method = RequestMethod.POST, value = "{id}/solution")
    public Challenge addSolution(@PathVariable String id, @RequestBody Solution solution) {
        return challengeService.addSolution(id, solution);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}/solution/{solutionId}")
    public void deleteSolution(@PathVariable String id, @PathVariable String solutionId) {
        challengeService.deleteSolution(id, solutionId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}/solution/{solutionId}")
    public Challenge updateSolution(@PathVariable String id, @PathVariable String solutionId,
                                    @RequestBody Solution solution) {
        return challengeService.updateSolution(id, solutionId, solution);
    }

    @RequestMapping(method = RequestMethod.POST, value = "{id}/{solutionId}/comment")
    public Challenge addSoluaddCommentToSolutiontion(@PathVariable String id, @PathVariable String solutionId,
                                                     @RequestBody Comment comment) {
        return challengeService.addCommentToSolution(id, solutionId, comment);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}/solution/{solutionId}/comment/{commentId}")
    public void deleteCommentFromSolution(@PathVariable String id, @PathVariable String solutionId, @PathVariable String commentId) {
        challengeService.deleteCommentFromSolution(id, solutionId, commentId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}/solution/{solutionId}/comment/{commentId}")
    public Challenge updateCommentInSolution(@PathVariable String id, @PathVariable String solutionId,
                                             @PathVariable String commentId, @RequestBody Comment comment) {
        return challengeService.updateCommentInSolution(id, solutionId, commentId, comment);
    }

}
