package com.lanlin.jinshuiqi.domain;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "jinshuiqi")
public class User {
  //配置表结构默认ID为索引 并且自增长
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY )
  @Column
  private Integer id;
  @Column
  private String username;
  @Column
  private String userpwd;
  @Column
  private String usermobile;
  @Column
  private String useraddr;
  @Column
  private Date userfirstinstall;
  @Column
  private Date usernextservice;
  @Column
  private String userinforemark;

  //无参构造
  public User() {
  }

  //用户名 密码简单构造
  public User(String username, String userpwd) {
    this.username = username;
    this.userpwd = userpwd;
  }
  //全参构造


  public User(String username, String userpwd, String usermobile, String useraddr, Date userfirstinstall,Date usernextservice, String userinforemark) {
    this.username = username;
    this.userpwd = userpwd;
    this.usermobile = usermobile;
    this.useraddr = useraddr;
    this.userfirstinstall = userfirstinstall;
    this.usernextservice = usernextservice;
    this.userinforemark = userinforemark;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUserpwd() {
    return userpwd;
  }

  public void setUserpwd(String userpwd) {
    this.userpwd = userpwd;
  }

  public String getUsermobile() {
    return usermobile;
  }

  public void setUsermobile(String usermobile) {
    this.usermobile = usermobile;
  }

  public String getUseraddr() {
    return useraddr;
  }

  public void setUseraddr(String useraddr) {
    this.useraddr = useraddr;
  }

  public Date getUserfirstinstall() {
    return userfirstinstall;
  }

  public void setUserfirstinstall(Date userfirstinstall) {
    this.userfirstinstall = userfirstinstall;
  }

  public Date getUsernextservice() {
    return usernextservice;
  }

  public void setUsernextservice(Date usernextservice) {
    this.usernextservice = usernextservice;
  }

  public String getUserinforemark() {
    return userinforemark;
  }

  public void setUserinforemark(String userinforemark) {
    this.userinforemark = userinforemark;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", userpwd='" + userpwd + '\'' +
            ", usermobile='" + usermobile + '\'' +
            ", useraddr='" + useraddr + '\'' +
            ", userfirstinstall=" + userfirstinstall +
            ", usernextservice=" + usernextservice +
            ", userinforemark='" + userinforemark + '\'' +
            '}';
  }
}
