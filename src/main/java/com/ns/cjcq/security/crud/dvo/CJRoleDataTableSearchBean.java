package com.ns.cjcq.security.crud.dvo;

import com.ns.cjcq.common.dataTables.entity.CJDataTablesSearchBean;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CJRoleDataTableSearchBean extends CJDataTablesSearchBean{
    private String name;
    private String roleTag;
    private String roleDesc;
}
