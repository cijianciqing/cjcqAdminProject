package com.ns.cjcq.common.dataTables.entity;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CJDataTablesSearchBean {
    private Integer draw;
    private Integer limit;
    private Integer start;
    private Integer page;
    //private String searchValue;
    //private String searchArea;
}
