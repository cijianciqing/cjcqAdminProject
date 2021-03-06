package com.ns.cjcq.security.crud.dvo;


import com.ns.cjcq.security.crud.domain.CJResource;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CJViewResource  {
    private Long id;
    private String name;
    private String url="#";
    private String resDesc;
    private String fontIcon;
    private Long parentId;
    private Integer sort;//类型为特殊声明
    @NotBlank(message = "资源类型不能为空")
    private String cjResourceType;

    @NotBlank(message = "资源类型不能为空")
    private String cjUrlType;
}
