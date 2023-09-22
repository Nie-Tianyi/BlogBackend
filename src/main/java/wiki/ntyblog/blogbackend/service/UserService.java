package wiki.ntyblog.blogbackend.service;

import org.springframework.stereotype.Service;
import wiki.ntyblog.blogbackend.dao.User;
import wiki.ntyblog.blogbackend.repos.UserRepo;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void createUser(String username, String password) throws NoSuchAlgorithmException {
        int salt = (int) (Math.random() * Integer.MAX_VALUE);
        User user = new User();
        user.setUsername(username);
        user.setSalt(salt);
        user.setPasswordHash(calculateHash(password, salt));
        userRepo.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username).get(0);
    }

    public boolean checkPassword(String username, String password) throws NoSuchAlgorithmException {
        User user = userRepo.findByUsername(username).get(0);
        return user.getPasswordHash().equals(calculateHash(password, user.getSalt()));
    }

    private String calculateHash(String password, int salt) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("sha256");
        instance.update(password.getBytes());
        instance.update(ByteBuffer.allocate(4).putInt(salt).array());
        byte[] digest = instance.digest();
        return Byte2Hex(digest);
    }

    private String Byte2Hex(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }

}
