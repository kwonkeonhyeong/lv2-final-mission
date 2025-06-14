package finalmission.stallstatus.entity;

import finalmission.member.entity.Member;
import finalmission.stall.entity.Stall;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class StallStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createAt;

    private String randomNickname;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Stall stall;

    protected StallStatus() {
    }

    public StallStatus(Long id, LocalDateTime createAt) {
        this.id = id;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StallStatus that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
