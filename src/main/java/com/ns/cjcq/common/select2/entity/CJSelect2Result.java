package com.ns.cjcq.common.select2.entity;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CJSelect2Result {
    List<CJSelect2Entity> results;
}
