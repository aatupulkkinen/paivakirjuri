package otp.model.daos.quote;

import otp.model.db.hiberante.CRUD;
import otp.model.entities.Quote;

import java.util.List;

public class QuoteDaoImpl extends CRUD implements QuoteDao {
    @Override
    public Quote get(int markId) {
        List<Quote> quotes;
        quotes = (List<Quote>) openWithTransaction(
                (session) -> session.createQuery("from Quote where markId = :mark_id")
                        .setParameter("mark_id", markId)
                        .list()
        );
        return quotes.size() > 0 ? quotes.get(quotes.size() - 1): null;
    }

    @Override
    public boolean insert(Quote quote) {
        Object result = openWithTransaction((session) -> {
            session.save(quote);
            return true;
        });
        return result != null;
    }
}
