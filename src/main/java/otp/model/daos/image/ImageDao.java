package otp.model.daos.image;

import otp.model.entities.Image;

public interface ImageDao {
    Image get(int markId);

    boolean insert(byte[] bytes, int markId);
}
