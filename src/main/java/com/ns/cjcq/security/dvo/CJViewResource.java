package com.ns.cjcq.security.dvo;


import com.ns.cjcq.common.zTree.entity.CJZTreeNodeEntity;
import com.ns.cjcq.security.domain.CJResource;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CJViewResource  {
    private Long id;
    private String name;
    private String url;
    private String resDesc;
    private String fontIcon;
    private Long parentId;
    private Integer sort;//类型为特殊声明
    private String cjResourceType;
}
