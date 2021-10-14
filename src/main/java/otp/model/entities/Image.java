package otp.model.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "images", schema = "test_eng")
public class Image implements Serializable {
    private int imageId;
    private byte[] content;
    private int markId;

    @Id
    @Column(name = "image_id", nullable = false)
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Basic
    @Column(name = "content", nullable = false)
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image that = (Image) o;
        return imageId == that.imageId && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, content);
    }

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }
}