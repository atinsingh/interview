package co.pragra.learning.inteviewresource.rest;

import co.pragra.learning.inteviewresource.entity.Company;
import co.pragra.learning.inteviewresource.entity.ErrorResponse;
import co.pragra.learning.inteviewresource.entity.User;
import co.pragra.learning.inteviewresource.exceptions.CompanyNotFoundException;
import co.pragra.learning.inteviewresource.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CompanyController {
    private CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping("/company")
    public ResponseEntity<?> getAll(
            @RequestParam(value = "name", required = false) Optional<String> name ,
            @RequestParam(value = "location", required = false) Optional<String> location

    ){
        List<Company> companies = service.getAll();
        if(name.isPresent() && !location.isPresent()) {
            companies = companies.stream().filter(c -> c.getCompanyName().toLowerCase().equals(name.get().toLowerCase())).collect(Collectors.toList());
            return ResponseEntity.ok(companies);
        }
        if(name.isPresent() && location.isPresent()) {
            companies = companies.stream().filter(c -> c.getCompanyName().toLowerCase().equals(name.get().toLowerCase()))
                    .filter(c->c.getLocation().toLowerCase().contains(location.get().toLowerCase())).collect(Collectors.toList());
            return ResponseEntity.ok(companies);
        }
        if(location.isPresent()&& !name.isPresent()) {
            companies = companies.stream()
                    .filter(c->c.getLocation().toLowerCase().contains(location.get().toLowerCase())).collect(Collectors.toList());
            return ResponseEntity.ok(companies);
        }
        return ResponseEntity.ok(companies);

    }

    @PostMapping(value = "/company")
    public Company create(@RequestBody Company company) {
        return service.createCompany(company);
    }

    @GetMapping("/company/{uuid}")
    public ResponseEntity<?> findById(@PathVariable String uuid){
        Company company = service.getById(UUID.fromString(uuid)).orElseThrow(() -> new CompanyNotFoundException("Company Not found"));
        return ResponseEntity.status(200).body(company);
    }

    private ResponseEntity<ErrorResponse> getErrorResponseResponseEntity(int status, String Not_Found) {
        return ResponseEntity.status(status)
                .body(ErrorResponse.builder()
                        .code(status)
                        .msg(Not_Found)
                        .errorTime(Instant.now())
                        .build()
                );
    }
}
