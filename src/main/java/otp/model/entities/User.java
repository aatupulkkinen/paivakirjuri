package otp.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "test_eng")
public class User implements Serializable {
    private String name;
    private String password;
    private String firstName;
    private String secondName;

    public User(String name, String password, String firstName, String secondName) {
        this.name = name;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public User() {
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
    @Column(name = "name", nullable = false, length = 40)
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 40)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 128)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, password, firstName, secondName);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name)
                && password.equals(user.password)
                && firstName.equals(user.firstName)
                && secondName.equals(user.secondName);
    }
}
