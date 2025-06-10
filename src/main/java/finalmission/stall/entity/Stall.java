package finalmission.stall.entity;

import finalmission.toilet.entity.Toilet;
import finalmission.user.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public class Stall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String warriorName;

    private String status;

    @ManyToOne
    private Toilet toilet;

    @ManyToOne
    private Member member;

    protected Stall() {}

    public Long getId() {
        return id;
    }

    public String getWarriorName() {
        return warriorName;
    }

    public String getStatus() {
        return status;
    }

    public Toilet getToilet() {
        return toilet;
    }

    public Member getUser() {
        return member;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Stall stall)) return false;
        return Objects.equals(id, stall.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
