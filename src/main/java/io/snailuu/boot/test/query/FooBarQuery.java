package io.snailuu.boot.test.query;

import io.snailuu.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * FooBar查询参数
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Data
@Schema(description = "FooBar查询参数")
public class FooBarQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

}

