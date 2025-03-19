package org.example.springboottoursystem.domain;

import jakarta.persistence.*;

@Table(name = "user")  //说明此实体类对应于数据库的user表
@Entity  //说明此类是个实体类
public class User {
    // 注意属性名要与数据表中的字段名一致
    // 主键自增int(10)对应long

    /*主键uid要加上这两个注解*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long uid;

    // 用户名属性varchar对应String（varchar用于存储可变长度字符串）
    private String uname;

    // 密码属性varchar对应String
    private String password;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
