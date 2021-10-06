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
    public Boolean insert(String name, String password) {
        FileOutputStream fout = null;
        try {
            String path = System.getProperty("java.io.tmpdir");
            fout = new FileOutputStream("userdata1234.aaa");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            User user = new User();
            user.setName(name);
            user.setPassword(password);
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
        insert(current.getName(), current.getPassword());
        return current;
    }
}
