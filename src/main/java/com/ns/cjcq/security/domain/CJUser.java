package com.ns.cjcq.security.domain;


//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import com.ns.cjcq.common.entity.CJBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//逻辑删除注解，删除sql变成了update
@SQLDelete(sql = "update cj_auth_user set cjuniversal_del_status = 1 where id = ?")
//@SQLDeleteAll(sql = "update cj_security_roleToUser set cjuniversal_del_status = 1 where id = ?")
//where条件带上了逻辑删除条件
@Where(clause = "cjuniversal_del_status = 0")
@Entity
@Table(name = "cj_auth_user")
//public class CJUser implements UserDetails{
public class CJUser extends CJBaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String showname;
    private String password;
    private String telephoneNo;
    private String email;
    // 表示帐号是否未过期
    private Boolean isAccountNonExpired = Boolean.TRUE;
    //表示帐号是否未锁定
    private Boolean isAccountNonLocked = Boolean.TRUE;
    //表示登录凭据是否未过期
    private Boolean isCredentialsNonExpired = Boolean.TRUE;
    //表示账户是否被禁用
    private Boolean isEnabled = Boolean.TRUE;

    @OneToMany(mappedBy = "cjUser", cascade = CascadeType.REMOVE)
    private Set<CJUserAndRole> cjUserAndRoles;


    /*
            * UserDetails相关接口--开始
            * */
  /*  @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }*/

    /*
    * UserDetails相关接口--结束
    * */


    @Override
    public String toString() {
        return "CJUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", telephoneNo='" + telephoneNo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

