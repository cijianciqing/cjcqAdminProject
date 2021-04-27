package com.ns.cjcq.security.dvo;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CJViewUser {
    private Long id;
    @NotBlank(message = "账号不能为空")
    private String username;
    private String showname;
    //@NotBlank(message = "用户电话不能为空")
    private String telephoneNo;
    //@NotBlank(message = "用户邮件不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    //表示账户是否被禁用
    private Boolean isEnabled=true;
}
