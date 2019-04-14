package org.indepth.intellibuild.extension;

/**
 * Created by sud on 13/4/19.
 */
public class IntelliBuildExtension {
  private String master;
  private String username;
  private String password;

  public String getMaster() {
    return master;
  }

  public void setMaster(String master) {
    this.master = master;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "IntelliBuildExtension{" +
        "master='" + master + '\'' +
        ", username='" + username + '\'' +
        ", password=*******" +
        '}';
  }
}
