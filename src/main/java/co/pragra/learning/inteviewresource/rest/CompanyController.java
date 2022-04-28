package co.pragra.learning.inteviewresource.rest;

import co.pragra.learning.inteviewresource.entity.Company;
import co.pragra.learning.inteviewresource.entity.ErrorResponse;
import co.pragra.learning.inteviewresource.entity.User;
import co.pragra.learning.inteviewresource.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CompanyController {
    private CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping("/company")
    public List<Company> getAll(){
        return service.getAll();
    }

    @PostMapping(value = "/company")
    public Company create(@RequestBody Company company) {
        return service.createCompany(company);
    }

    @GetMapping("/company/{uuid}")
    public ResponseEntity<?> findById(@PathVariable String uuid){
        try {
            Optional<Company> company = service.getById(UUID.fromString(uuid));
            if (company.isPresent()) {
                return
                        ResponseEntity.status(200)
                                .body(company.get());
            }else {
                return getErrorResponseResponseEntity(404, "Not Found");
            }
        }catch (Exception ex){
            return getErrorResponseResponseEntity(400, "Invalid UUID");
        }
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
