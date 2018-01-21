package co.fkch.controller;

import co.fkch.domain.Company;
import co.fkch.repository.ChallengeTagRepository;
import co.fkch.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyRestController {

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private ChallengeTagRepository challengeTagRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Company> getAll() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Company createOrGet(@RequestBody Company company) {
        Company c = repository.findByCompanyNameIgnoreCase(company.getCompanyName());
        if (c == null) {
            c = repository.insert(company);
        }
        return c;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable String id) {
        repository.delete(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{companyName}")
    public void deleteByCompanyName(@PathVariable String companyName) {
        Company c = repository.findByCompanyNameIgnoreCase(companyName);
        if (c != null) {
            repository.delete(c);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public Company update(@PathVariable String id, @RequestBody Company company) {
        Company c = repository.findOne(id);
        c.setCompanyName(company.getCompanyName());
        return repository.save(c);
    }

}
