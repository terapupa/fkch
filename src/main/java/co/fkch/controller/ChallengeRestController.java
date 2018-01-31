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
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeRestController {

    @Autowired
    private ChallengeService challengeService;

    @RequestMapping(method = RequestMethod.GET)
    public GeneralResponse getAll(Pageable pageable) {
        GeneralResponse gp = new GeneralResponse<>(challengeService.getAll(pageable));
        gp.add(linkTo(methodOn(ChallengeRestController.class).getAll(pageable)).withSelfRel());
        return gp;
    }

    @RequestMapping(method = RequestMethod.GET, value = "company")
    public GeneralResponse getByCompanyName(@RequestParam String company, Pageable pageable) {
        GeneralResponse gp = new GeneralResponse<>(challengeService.getByCompanyName(company, pageable));
        gp.add(linkTo(methodOn(ChallengeRestController.class).getByCompanyName(company, pageable)).withSelfRel());
        return gp;
    }

    @RequestMapping(method = RequestMethod.GET, value = "tags")
    public GeneralResponse findByChallengeTags(@RequestParam Collection<String> tags, Pageable pageable) {
        GeneralResponse gp = new GeneralResponse<>(challengeService.findByChallengeTags(tags, pageable));
        gp.add(linkTo(methodOn(ChallengeRestController.class).findByChallengeTags(tags, pageable)).withSelfRel());
        return gp;
    }

    @RequestMapping(method = RequestMethod.POST)
    public GeneralResponse create(@RequestBody Challenge challenge) {
        GeneralResponse gp = new GeneralResponse<>(challengeService.create(challenge));
        gp.add(linkTo(methodOn(ChallengeRestController.class).create(challenge)).withSelfRel());
        return gp;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public GeneralResponse delete(@PathVariable String id) {
        challengeService.delete(id);
        GeneralResponse gp = new GeneralResponse<>();
        gp.add(linkTo(methodOn(ChallengeRestController.class).delete(id)).withSelfRel());
        return gp;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public GeneralResponse update(@PathVariable String id, @RequestBody Challenge challenge) {
        GeneralResponse gp = new GeneralResponse<>(challengeService.update(id, challenge));
        gp.add(linkTo(methodOn(ChallengeRestController.class).update(id, challenge)).withSelfRel());
        return gp;
    }

    @RequestMapping(method = RequestMethod.POST, value = "{id}/comment")
    public GeneralResponse addComment(@PathVariable String id, @RequestBody Comment comment) {
        GeneralResponse gp = new GeneralResponse<>(challengeService.addComment(id, comment));
        gp.add(linkTo(methodOn(ChallengeRestController.class).addComment(id, comment)).withSelfRel());
        return gp;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}/comment/{commentId}")
    public GeneralResponse deleteComment(@PathVariable String id, @PathVariable String commentId) {
        GeneralResponse gp = new GeneralResponse<>(challengeService.deleteComment(id, commentId));
        gp.add(linkTo(methodOn(ChallengeRestController.class).deleteComment(id, commentId)).withSelfRel());
        return gp;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}/comment/{commentId}")
    public GeneralResponse updateComment(@PathVariable String id, @PathVariable String commentId,  @RequestBody Comment comment) {
        GeneralResponse gp = new GeneralResponse<>(challengeService.updateComment(id, commentId, comment));
        gp.add(linkTo(methodOn(ChallengeRestController.class).updateComment(id, commentId, comment)).withSelfRel());
        return gp;
    }

    @RequestMapping(method = RequestMethod.POST, value = "{id}/solution")
    public GeneralResponse addSolution(@PathVariable String id, @RequestBody Solution solution) {
        GeneralResponse gp = new GeneralResponse<>(challengeService.addSolution(id, solution));
        gp.add(linkTo(methodOn(ChallengeRestController.class).addSolution(id, solution)).withSelfRel());
        return gp;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}/solution/{solutionId}")
    public GeneralResponse deleteSolution(@PathVariable String id, @PathVariable String solutionId) {
        GeneralResponse gp = new GeneralResponse<>(challengeService.deleteSolution(id, solutionId));
        gp.add(linkTo(methodOn(ChallengeRestController.class).deleteSolution(id, solutionId)).withSelfRel());
        return gp;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}/solution/{solutionId}")
    public GeneralResponse updateSolution(@PathVariable String id, @PathVariable String solutionId,
                                    @RequestBody Solution solution) {
        GeneralResponse gp = new GeneralResponse<>(challengeService.updateSolution(id, solutionId, solution));
        gp.add(linkTo(methodOn(ChallengeRestController.class).updateSolution(id, solutionId, solution)).withSelfRel());
        return gp;
    }

    @RequestMapping(method = RequestMethod.POST, value = "{id}/{solutionId}/comment")
    public GeneralResponse addCommentToSolution(@PathVariable String id, @PathVariable String solutionId,
                                                @RequestBody Comment comment) {
        GeneralResponse gp = new GeneralResponse<>(challengeService.addCommentToSolution(id, solutionId, comment));
        gp.add(linkTo(methodOn(ChallengeRestController.class)
                .addCommentToSolution(id, solutionId, comment)).withSelfRel());
        return gp;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}/solution/{solutionId}/comment/{commentId}")
    public GeneralResponse deleteCommentFromSolution(@PathVariable String id, @PathVariable String solutionId,
                                                     @PathVariable String commentId) {
        GeneralResponse gp = new GeneralResponse<>(challengeService.deleteCommentFromSolution(id, solutionId, commentId));
        gp.add(linkTo(methodOn(ChallengeRestController.class)
                .deleteCommentFromSolution(id, solutionId, commentId)).withSelfRel());
        return gp;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}/solution/{solutionId}/comment/{commentId}")
    public GeneralResponse updateCommentInSolution(@PathVariable String id, @PathVariable String solutionId,
                                             @PathVariable String commentId, @RequestBody Comment comment) {
        GeneralResponse gp = new GeneralResponse<>(challengeService.updateCommentInSolution(
                id, solutionId, commentId, comment));
        gp.add(linkTo(methodOn(ChallengeRestController.class)
                .updateCommentInSolution(id, solutionId, commentId, comment)).withSelfRel());
        return gp;
    }

}
