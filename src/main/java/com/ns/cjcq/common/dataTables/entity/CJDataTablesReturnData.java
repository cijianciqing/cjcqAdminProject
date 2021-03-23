package com.ns.cjcq.common.dataTables.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CJDataTablesReturnData<T> {
    private Integer draw;
    private Long total;
    private List<T> data;
}
