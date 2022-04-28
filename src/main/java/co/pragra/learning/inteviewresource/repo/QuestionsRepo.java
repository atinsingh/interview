package co.pragra.learning.inteviewresource.repo;

import co.pragra.learning.inteviewresource.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionsRepo extends JpaRepository<Questions, UUID> {
    @Query("select p from Questions p where p.company.id=:uuid")
    List<Questions> getByCompany(@Param("uuid") UUID uuid);
}

