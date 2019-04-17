package library.model;

import library.annotation.Table;
import library.annotation.TableField;

@Table("User")
public class User {

    public final static String ADMIN = "admin";

    public final static String GUEST = "guest";

    @TableField("u_id")
    private Long id;

    @TableField("u_name")
    private String userName;

    @TableField("u_pwd")
    private String userPwd;

    @TableField("u_identity")
    private String identity;//身份

    @TableField("u_mailBox")
    private String mailBox;//邮箱

    public User() {}

    public User(Long id, String userName, String userPwd, String identity, String mailBox) {
        this.id = id;
        this.userName = userName;
        this.userPwd = userPwd;
        this.identity = identity;
        this.mailBox = mailBox;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getMailBox() {
        return mailBox;
    }

    public void setMailBox(String mailBox) {
        this.mailBox = mailBox;
    }
}
