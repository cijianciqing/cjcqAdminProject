package com.ns.cjcq.common.zTree.entity;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CJZTreeNodeResourceEntity extends CJZTreeNodeEntity{
    private int sort;
    private String cjResourceType;
}
