package otp.model.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "quotes", schema = "test_eng")
public class Quote {
    private int quoteId;
    private String content;

    @Id
    @Column(name = "quote_id", nullable = false)
    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 150)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote that = (Quote) o;
        return quoteId == that.quoteId && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quoteId, content);
    }
}
