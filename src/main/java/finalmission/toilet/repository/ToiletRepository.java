package finalmission.toilet.repository;

import finalmission.toilet.entity.Toilet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToiletRepository extends JpaRepository<Toilet, Long> {
}
