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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//逻辑删除注解，删除sql变成了update
@SQLDelete(sql = "update cj_auth_resource set cjuniversal_del_status = 1 where id = ?")
//@SQLDeleteAll(sql = "update cj_security_roleToUser set cjuniversal_del_status = 1 where id = ?")
//where条件带上了逻辑删除条件
@Where(clause = "cjuniversal_del_status = 0")
@Entity
@Table(name="cj_auth_resource")
public class CJResource  extends CJBaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    private String resDesc;

    private String fontIcon;
    /**
     * 资源类型
     */
    @Enumerated(EnumType.STRING)
    private CJResourceType cjResourceType;

    /**
     * 序号
     */
    private int sort;

    /**
     * 父资源
     */
    @ManyToOne
    private CJResource parent;
    /**
     * 子资源
     */
    @OneToMany(mappedBy = "parent",cascade = {CascadeType.REMOVE})
    @OrderBy("sort ASC")
    private List<CJResource> childs = new ArrayList<>();

    @OneToMany(mappedBy = "cjResource",cascade = CascadeType.REMOVE)
    private Set<CJRoleAndResource> cjRoleAndResources;

    @Override
    public String toString() {
        return "CJResource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", resDesc='" + resDesc + '\'' +
                ", fontIcon='" + fontIcon + '\'' +
                ", sort=" + sort +
                '}';
    }
}
