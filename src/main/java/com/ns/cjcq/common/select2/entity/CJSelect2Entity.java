package com.ns.cjcq.common.select2.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CJSelect2Entity {
    private Long id;//option value
    private String text;//option显示
    private boolean selected;//选中
    private boolean disabled;//禁用
}
