package com.ns.cjcq.security.dvo;

import com.ns.cjcq.common.dataTables.entity.CJDataTablesSearchBean;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CJUserDataTableSearchBean extends CJDataTablesSearchBean{
    private String username;
    private String showname;
    private String telephoneNo;
    private String email;
}
