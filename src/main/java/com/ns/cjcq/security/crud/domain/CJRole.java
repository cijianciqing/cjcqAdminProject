package com.ns.cjcq.security.crud.domain;

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
@SQLDelete(sql = "update cj_auth_role set cjuniversal_del_status = 1 where id = ?")
//where条件带上了逻辑删除条件
@Where(clause = "cjuniversal_del_status = 0")
@Entity
@Table(name="cj_auth_role")
public class CJRole  extends CJBaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    /*
    * 英文标识，需要备注必填，且必须为英文+特殊符号+数字。。。
    * */
    private String roleTag;
    private String roleDesc;

    @OneToMany(mappedBy = "cjRole",cascade = CascadeType.REMOVE)
    private Set<CJUserAndRole> cjUserAndRoles;

    @OneToMany(mappedBy = "cjRole",cascade = CascadeType.REMOVE)
    private Set<CJRoleAndResource> cjRoleAndResources;


    @Override
    public String toString() {
        return "CJRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roleTag='" + roleTag + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }
}
