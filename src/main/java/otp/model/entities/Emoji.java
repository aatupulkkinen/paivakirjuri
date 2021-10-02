package otp.model.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "emojis", schema = "test_eng")
public class Emoji {
    private int emojiId;
    private String path;

    @Id
    @Column(name = "emoji_id", nullable = false)
    public int getEmojiId() {
        return emojiId;
    }

    public void setEmojiId(int emojiId) {
        this.emojiId = emojiId;
    }

    @Basic
    @Column(name = "path", nullable = false, length = 64)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emoji that = (Emoji) o;
        return emojiId == that.emojiId && Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emojiId, path);
    }
}
