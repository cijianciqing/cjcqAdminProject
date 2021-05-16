package com.ns.cjcq.common.zTree.entity;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CJZTreeNodeEntity {
    private Long id;
    private Long pid;
    private String name;
    private String title;
    private String url = "#";
    private boolean nocheck;//设置节点是否隐藏 checkbox / radio
    private boolean checked;//节点的 checkBox / radio 的 勾选状态。
//    private boolean isParent  = false;
}
