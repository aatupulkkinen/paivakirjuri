package otp.model.daos.mark;

import otp.model.db.hiberante.CRUD;
import otp.model.entities.Mark;

import java.util.ArrayList;
import java.util.List;

public class MarkDaoImpl extends CRUD implements MarkDao {
    @Override
    public List<Mark> getAll(String username) {
        List<Mark> marks = new ArrayList<>();
        marks = (List<Mark>) openWithTransaction(
                (session) -> session.createQuery("from Mark where name = :n")
                        .setParameter("n", username)
                        .list()
        );
        return marks;
    }

    @Override
    public boolean insert(Mark mark) {
        Object result = openWithTransaction((session) -> {
            session.save(mark);
            return true;
        });
        return result != null;
    }
}