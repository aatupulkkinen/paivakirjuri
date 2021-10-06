package otp.model.daos;

import otp.model.entities.User;

import java.io.*;

public class UserLocal implements UserDao {

    @Override
    public User get(String name, String password) {
        FileInputStream fout = null;
        try {
            String path = System.getProperty("java.io.tmpdir");
            fout = new FileInputStream("userdata1234.aaa");
            ObjectInputStream oos = new ObjectInputStream(fout);
            return (User) oos.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean insert(User user) {
        FileOutputStream fout = null;
        try {
            String path = System.getProperty("java.io.tmpdir");
            fout = new FileOutputStream("userdata1234.aaa");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(user);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User changePassword(User user, String newPass) {
        User current = get(user.getName(), user.getPassword());
        current.setPassword(newPass);
        insert(new User(current.getName(), current.getPassword(), current.getFirstName(), current.getSecondName()));
        return current;
    }
}
