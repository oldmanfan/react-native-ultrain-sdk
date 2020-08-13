package com.ultrain.sdk.deps.data.remote.model.api.tablerows;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * @author redli
 * @date 2019/3/29
 */
public class TableRows {

    /**
     * rows : [{"account":"wb","balance":"6.5000 SAK","used":223},{"account":"sakuya","balance":"8.0000 SAK","used":333}]
     * more : false
     */

    @Expose
    private boolean more;

    @Expose
    private List<Rows> rows;

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public List<Rows> getRows() {
        return rows;
    }

    public void setRows(List<Rows> rows) {
        this.rows = rows;
    }
}
