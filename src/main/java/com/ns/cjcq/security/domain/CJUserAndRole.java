package com.ns.cjcq.security.domain;

import com.ns.cjcq.common.entity.CJBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//逻辑删除注解，删除sql变成了update
@SQLDelete(sql = "update cj_auth_user_and_role set cjuniversal_del_status = 1 where id = ?")
//@SQLDeleteAll(sql = "update cj_security_roleToUser set cjuniversal_del_status = 1 where id = ?")
//where条件带上了逻辑删除条件
@Where(clause = "cjuniversal_del_status = 0")
@Entity
@Table(name="cj_auth_user_and_role")
public class CJUserAndRole  extends CJBaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = CJUser.class,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private CJUser cjUser;

    @ManyToOne(targetEntity = CJRole.class,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private CJRole cjRole;

}
