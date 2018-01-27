package co.fkch.controller;

import co.fkch.domain.Challenge;
import co.fkch.domain.Comment;
import co.fkch.domain.Solution;
import co.fkch.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/challenges")
public class ChallengeRestController {

    @Autowired
    private ChallengeService challengeService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAll() {
        return new ResponseEntity<>(new GeneralResponse<>(challengeService.getAll()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "company/{companyName}")
    @ResponseBody
    public ResponseEntity getByCompanyName(@PathVariable String companyName) {
        return new ResponseEntity<>(new GeneralResponse<>(challengeService.getByCompanyName(companyName)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity create(@RequestBody Challenge challenge) {
        return new ResponseEntity<>(new GeneralResponse<>(challengeService.create(challenge)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable String id) {
        challengeService.delete(id);
        return new ResponseEntity<>(new GeneralResponse<>(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable String id, @RequestBody Challenge challenge) {
        return new ResponseEntity<>(new GeneralResponse<>(challengeService.update(id, challenge)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "{id}/comment")
    @ResponseBody
    public ResponseEntity addComment(@PathVariable String id, @RequestBody Comment comment) {
        return new ResponseEntity<>(new GeneralResponse<>(challengeService.addComment(id, comment)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}/comment/{commentId}")
    @ResponseBody
    public ResponseEntity deleteComment(@PathVariable String id, @PathVariable String commentId) {
        return new ResponseEntity<>(new GeneralResponse<>(challengeService.deleteComment(id, commentId)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}/comment/{commentId}")
    @ResponseBody
    public ResponseEntity updateComment(@PathVariable String id, @PathVariable String commentId,  @RequestBody Comment comment) {
        return new ResponseEntity<>(new GeneralResponse<>(challengeService.updateComment(id, commentId, comment)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "{id}/solution")
    @ResponseBody
    public ResponseEntity addSolution(@PathVariable String id, @RequestBody Solution solution) {
        return new ResponseEntity<>(new GeneralResponse<>(challengeService.addSolution(id, solution)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}/solution/{solutionId}")
    @ResponseBody
    public ResponseEntity deleteSolution(@PathVariable String id, @PathVariable String solutionId) {
        return new ResponseEntity<>(new GeneralResponse<>(challengeService.deleteSolution(id, solutionId)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}/solution/{solutionId}")
    @ResponseBody
    public ResponseEntity updateSolution(@PathVariable String id, @PathVariable String solutionId,
                                    @RequestBody Solution solution) {
        return new ResponseEntity<>(new GeneralResponse<>(challengeService.updateSolution(id, solutionId, solution)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "{id}/{solutionId}/comment")
    @ResponseBody
    public ResponseEntity addSoluaddCommentToSolutiontion(@PathVariable String id, @PathVariable String solutionId,
                                                     @RequestBody Comment comment) {
        return new ResponseEntity<>(new GeneralResponse<>(challengeService.addCommentToSolution(id, solutionId, comment)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}/solution/{solutionId}/comment/{commentId}")
    @ResponseBody
    public ResponseEntity deleteCommentFromSolution(@PathVariable String id, @PathVariable String solutionId, @PathVariable String commentId) {
        return new ResponseEntity<>(new GeneralResponse<>(challengeService.deleteCommentFromSolution(id, solutionId, commentId)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}/solution/{solutionId}/comment/{commentId}")
    @ResponseBody
    public ResponseEntity updateCommentInSolution(@PathVariable String id, @PathVariable String solutionId,
                                             @PathVariable String commentId, @RequestBody Comment comment) {
        return new ResponseEntity<>(new GeneralResponse<>(challengeService.updateCommentInSolution(
                id, solutionId, commentId, comment)), HttpStatus.OK);
    }

}
