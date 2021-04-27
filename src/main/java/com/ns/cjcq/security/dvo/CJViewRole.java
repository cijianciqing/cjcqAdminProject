package com.ns.cjcq.security.dvo;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CJViewRole {
    private Long id;
    @NotBlank(message = "角色名称不能为空")
    private String name;
    @NotBlank(message = "角色标识不能为空")
    private String roleTag;
    private String roleDesc;
}
