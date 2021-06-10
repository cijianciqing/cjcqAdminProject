package com.ns.cjcq.security.crud.dvo;


import com.ns.cjcq.common.select2.entity.CJSelect2Entity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CJEditUser extends CJViewUser{
    @NotBlank(message = "密码不能为空")
    private String password;

    private List<CJSelect2Entity> userRoles;
}
