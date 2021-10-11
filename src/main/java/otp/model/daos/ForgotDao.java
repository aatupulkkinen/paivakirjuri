package otp.model.daos;

import otp.model.entities.Code;

public interface ForgotDao {
    Code get(String username);
    boolean insert(Code code);
}
