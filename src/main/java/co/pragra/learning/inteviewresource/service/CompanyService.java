package co.pragra.learning.inteviewresource.service;

import co.pragra.learning.inteviewresource.entity.Company;
import co.pragra.learning.inteviewresource.entity.Status;
import co.pragra.learning.inteviewresource.repo.CompanyRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {
    private CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public Company createCompany(Company company) {
        company.setCreateDate(Instant.now());
        company.setUpdateDate(Instant.now());
        company.setStatus(Status.ACTIVE);
        return repository.save(company);
    }

    public Optional<Company> getById(UUID uuid) {
        return repository.findById(uuid);
    }

    public List<Company> getAll(){
        return repository.findAll();
    }
}
