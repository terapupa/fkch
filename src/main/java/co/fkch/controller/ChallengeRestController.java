package co.fkch.controller;

import co.fkch.domain.Challenge;
import co.fkch.domain.Comment;
import co.fkch.domain.Solution;
import co.fkch.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeRestController {

    @Autowired
    private ChallengeService challengeService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public GeneralResponse getAll(Pageable pageable) {
        return new GeneralResponse<>(challengeService.getAll(pageable));
    }

    @RequestMapping(method = RequestMethod.GET, value = "company")
    @ResponseBody
    public GeneralResponse getByCompanyName(@RequestParam String company, Pageable pageable) {
        return new GeneralResponse<>(challengeService.getByCompanyName(company, pageable));
    }

    @RequestMapping(method = RequestMethod.GET, value = "tags")
    @ResponseBody
    public GeneralResponse findByChallengeTags(@RequestParam Collection<String> tags, Pageable pageable) {
        return new GeneralResponse<>(challengeService.findByChallengeTags(tags, pageable));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public GeneralResponse create(@RequestBody Challenge challenge) {
        return new GeneralResponse<>(challengeService.create(challenge));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    @ResponseBody
    public GeneralResponse delete(@PathVariable String id) {
        challengeService.delete(id);
        return new GeneralResponse<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    @ResponseBody
    public GeneralResponse update(@PathVariable String id, @RequestBody Challenge challenge) {
        return new GeneralResponse<>(challengeService.update(id, challenge));
    }

    @RequestMapping(method = RequestMethod.POST, value = "{id}/comment")
    @ResponseBody
    public GeneralResponse addComment(@PathVariable String id, @RequestBody Comment comment) {
        return new GeneralResponse<>(challengeService.addComment(id, comment));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}/comment/{commentId}")
    @ResponseBody
    public GeneralResponse deleteComment(@PathVariable String id, @PathVariable String commentId) {
        return new GeneralResponse<>(challengeService.deleteComment(id, commentId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}/comment/{commentId}")
    @ResponseBody
    public GeneralResponse updateComment(@PathVariable String id, @PathVariable String commentId,  @RequestBody Comment comment) {
        return new GeneralResponse<>(challengeService.updateComment(id, commentId, comment));
    }

    @RequestMapping(method = RequestMethod.POST, value = "{id}/solution")
    @ResponseBody
    public GeneralResponse addSolution(@PathVariable String id, @RequestBody Solution solution) {
        return new GeneralResponse<>(challengeService.addSolution(id, solution));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}/solution/{solutionId}")
    @ResponseBody
    public GeneralResponse deleteSolution(@PathVariable String id, @PathVariable String solutionId) {
        return new GeneralResponse<>(challengeService.deleteSolution(id, solutionId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}/solution/{solutionId}")
    @ResponseBody
    public GeneralResponse updateSolution(@PathVariable String id, @PathVariable String solutionId,
                                    @RequestBody Solution solution) {
        return new GeneralResponse<>(challengeService.updateSolution(id, solutionId, solution));
    }

    @RequestMapping(method = RequestMethod.POST, value = "{id}/{solutionId}/comment")
    @ResponseBody
    public GeneralResponse addSoluaddCommentToSolutiontion(@PathVariable String id, @PathVariable String solutionId,
                                                     @RequestBody Comment comment) {
        return new GeneralResponse<>(challengeService.addCommentToSolution(id, solutionId, comment));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}/solution/{solutionId}/comment/{commentId}")
    @ResponseBody
    public GeneralResponse deleteCommentFromSolution(@PathVariable String id, @PathVariable String solutionId, @PathVariable String commentId) {
        return new GeneralResponse<>(challengeService.deleteCommentFromSolution(id, solutionId, commentId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}/solution/{solutionId}/comment/{commentId}")
    @ResponseBody
    public GeneralResponse updateCommentInSolution(@PathVariable String id, @PathVariable String solutionId,
                                             @PathVariable String commentId, @RequestBody Comment comment) {
        return new GeneralResponse<>(challengeService.updateCommentInSolution(
                id, solutionId, commentId, comment));
    }

}
