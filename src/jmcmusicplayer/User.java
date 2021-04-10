package jmcmusicplayer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    
    String username;
    String password;
    
    public User(String un, String pw) {
        setUsername(un);
        setPassword(pw);
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = hashPassword(password);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Has hashing techniques">
    public static String hashPassword(String pw) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pw.getBytes());
            byte[] resultByteArray = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "Hash Password";
    }
    // </editor-fold>
}
