package otp.model.daos.mark;

import otp.model.entities.Mark;

import java.util.List;

public interface MarkDao {
    List<Mark> getAll(String username);
    int insert(Mark mark);
    boolean update(Mark mark);
}
