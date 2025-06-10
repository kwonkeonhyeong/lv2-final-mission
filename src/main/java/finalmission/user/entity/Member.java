package finalmission.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;
import java.util.regex.Pattern;

@Entity
public class Member {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    protected Member() {}

    public Member(String email, String password) {
        validate(email, password);
        this.email = email;
        this.password = password;
    }

    private void validate(String email, String password) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("이메일은 비어있을 수 없습니다.");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("유효하지 않은 이메일 형식입니다.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("패스워드는 비어있을 수 없습니다.");
        }
    }

    public boolean checkInvalidLogin(String email, String password) {
        return !(this.email.equals(email) && this.password.equals(password));
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Member member)) return false;
        return Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
