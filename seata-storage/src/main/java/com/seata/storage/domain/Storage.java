package com.seata.storage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 180465
 * @date 2020/7/7 19:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    private Long id;

    private Long product_id;

    private Integer total;

    private Integer residue;

    private Integer used;
}
