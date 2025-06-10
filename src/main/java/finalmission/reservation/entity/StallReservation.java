package finalmission.reservation.entity;

import finalmission.toilet.entity.Stall;
import finalmission.user.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public class StallReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String warriorName;

    private String status;

    @ManyToOne
    private Stall stall;

    @ManyToOne
    private Member member;

    protected StallReservation() {}

    public StallReservation(String warriorName, String status, Stall stall, Member member) {
        this.warriorName = warriorName;
        this.status = status;
        this.stall = stall;
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public String getWarriorName() {
        return warriorName;
    }

    public String getStatus() {
        return status;
    }

    public Stall getToilet() {
        return stall;
    }

    public Member getUser() {
        return member;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StallReservation stallReservation)) return false;
        return Objects.equals(id, stallReservation.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
