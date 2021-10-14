package otp.model.daos.image;

import otp.model.db.hiberante.CRUD;
import otp.model.entities.Image;

import java.util.List;

public class ImageDaoImpl extends CRUD implements ImageDao {

    @Override
    public boolean insert(byte[] bytes, int markId) {
        System.out.println(markId);
        Object result = openWithTransaction((session) -> {
            Image image = new Image();
            image.setContent(bytes);
            image.setMarkId(markId);
            session.save(image);
            return true;
        });
        return result != null;
    }

    @Override
    public Image get(int markId) {
        List<Image> images;
        images = (List<Image>) openWithTransaction(
                (session) -> session.createQuery("from Image where markId = :mark_id")
                        .setParameter("mark_id", markId)
                        .list()
        );
        return images.size() > 0 ? images.get(images.size() - 1) : null;
    }

}
