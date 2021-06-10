package com.ns.cjcq.security.crud.domain;

import com.ns.cjcq.common.entity.CJBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//逻辑删除注解，删除sql变成了update
@SQLDelete(sql = "update cj_auth_role_and_resource set cjuniversal_del_status = 1 where id = ?")
@SQLDeleteAll(sql = "update cj_auth_role_and_resource set cjuniversal_del_status = 1 where id = ?")
//where条件带上了逻辑删除条件
@Where(clause = "cjuniversal_del_status = 0")
@Entity
@Table(name="cj_auth_role_and_resource")
public class CJRoleAndResource  extends CJBaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = CJRole.class)
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private CJRole cjRole;

    @ManyToOne(targetEntity = CJResource.class)
    @JoinColumn(name = "resource_id",referencedColumnName = "id")
    private CJResource cjResource;

}
