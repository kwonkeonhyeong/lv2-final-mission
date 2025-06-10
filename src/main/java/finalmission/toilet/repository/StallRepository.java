package finalmission.toilet.repository;

import finalmission.toilet.entity.Stall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StallRepository extends JpaRepository<Stall, Long> {
    Optional<Stall> findById(Long id);
}
