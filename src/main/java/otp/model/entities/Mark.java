package otp.model.entities;

import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "marks", schema = "test_eng")
public class Mark {
    private String content;
    private Date created;
    private Date modified;
    private int id;
    private String color;
    private String name;

    @Override
    public String toString() {
        return "Mark{" +
                "content='" + content + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", id=" + id +
                ", color='" + color + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 10000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "created", nullable = false)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Basic
    @Column(name = "modified", nullable = false)
    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "color", nullable = true, length = 7)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark that = (Mark) o;
        return id == that.id && Objects.equals(content, that.content) && Objects.equals(created, that.created) && Objects.equals(modified, that.modified) && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, created, modified, id, color);
    }
}
