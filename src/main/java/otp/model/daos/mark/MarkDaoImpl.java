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
    public int insert(Mark mark) {
        Object result = openWithTransaction((session) -> {
            return (Integer) session.save(mark);
        });
        try {
            return (Integer) result;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public boolean update(Mark mark) {
        Object result = openWithTransaction((session) -> {
            session.saveOrUpdate(mark);
            return true;
        });
        return result != null;
    }
}