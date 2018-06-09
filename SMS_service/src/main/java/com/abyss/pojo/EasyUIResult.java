package com.abyss.pojo;

import java.util.List;


public class EasyUIResult {
    private Long total;
    private List<?> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "EasyUIResult [total=" + total + ", rows=" + rows + "]";
    }

}
