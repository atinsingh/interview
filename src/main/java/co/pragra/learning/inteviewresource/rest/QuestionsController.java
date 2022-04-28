package co.pragra.learning.inteviewresource.rest;

import co.pragra.learning.inteviewresource.entity.Company;
import co.pragra.learning.inteviewresource.entity.ErrorResponse;
import co.pragra.learning.inteviewresource.entity.Questions;
import co.pragra.learning.inteviewresource.entity.User;
import co.pragra.learning.inteviewresource.repo.QuestionsRepo;
import co.pragra.learning.inteviewresource.service.CompanyService;
import co.pragra.learning.inteviewresource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class QuestionsController {
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private QuestionsRepo questionsRepo;

    @PostMapping("/company/{uuid}/questions")
    public ResponseEntity<?> addQuestion(@RequestBody Questions questions, @PathVariable String uuid){
        Optional<Company> company = companyService.getById(UUID.fromString(uuid));
       // User user = userService.getByID(questions.getUser().getId());

        if(company.isPresent()) {
           // questions.setUser(user);
            questions.setCompany(company.get());
            questions.setCreateDate(Instant.now());
            questions.setUpdateDate(Instant.now());
            questionsRepo.save(questions);
            return
                    ResponseEntity.ok().body(questionsRepo.findAll());
        }else {
            return ResponseEntity.status(400)
                    .body(ErrorResponse.builder()
                            .code(400)
                            .msg("Bad Request")
                            .errorTime(Instant.now())
                            .build()
                    );
        }
    }

    @DeleteMapping("/questions/{uuid}")
    public ResponseEntity<?> deleteById(@PathVariable UUID uuid){
        questionsRepo.deleteById(uuid);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/company/{uuid}/questions")
    public List<Questions> getAllByCompanyId(@PathVariable("uuid") String uuid){
        System.out.println("uuid = " + uuid);
        return questionsRepo.getByCompany(UUID.fromString(uuid));
    }

}
