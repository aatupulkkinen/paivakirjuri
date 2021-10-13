package otp.model.daos.quote;

import otp.model.entities.Quote;

public interface QuoteDao {
    Quote get(int markId);
    boolean insert(Quote quote);
}
