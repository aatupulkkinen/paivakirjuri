package otp.model.entities;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "recovery_codes", schema = "test_eng")
public class Code {

    public Code() {
    }

    public Code(String name, String code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Code code1 = (Code) o;
        return Objects.equals(name, code1.name) && Objects.equals(code, code1.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code);
    }

    @Id
    @Column(name = "name", nullable = false, length = 40)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "code", nullable = false, length = 12)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    private String name;
    private String code;

}

