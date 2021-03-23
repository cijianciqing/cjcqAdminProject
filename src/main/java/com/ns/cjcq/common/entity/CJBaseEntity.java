package com.ns.cjcq.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@MappedSuperclass//实体继承映射
public class CJBaseEntity implements Serializable {
//    @Id
//    @Column(name = "ID")
//    @ApiModelProperty(value = "主键")
//    private Long id;

    //删除标志位
    @Basic
    @Column(name = "cjuniversal_del_status")
    @JsonIgnore//转换到json时，不显示此字段
    private Integer delStatus = 0;

    //实体数据最后更新时间
    @Column(name = "cjuniversal_update_time")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @JsonIgnore//转换到json时，不显示此字段
    private Date updateTime;

    //实体数据创建时间
    @Column(name = "cjuniversal_create_time",updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonIgnore//转换到json时，不显示此字段
    private Date createTime;
}
