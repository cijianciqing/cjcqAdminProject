package com.ns.cjcq.security.dvo;


import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
/*
* 用于前端，左侧导航栏展示
* */
public class CJPermissionResource implements  Comparable<CJPermissionResource>{
    private Long id;
    private String name;
    private String url;
    private String resDesc;
    private String fontIcon;
    private Integer sort;//类型为特殊声明
    private String cjResourceType;
    private List<CJPermissionResource> childs;

    @Override
    public int compareTo(CJPermissionResource o) {
        return (int) (this.id - o.id);
    }
}
