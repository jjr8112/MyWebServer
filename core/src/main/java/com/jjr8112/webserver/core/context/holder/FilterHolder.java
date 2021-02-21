package com.jjr8112.webserver.core.context.holder;

import com.jjr8112.webserver.core.filter.Filter;
import lombok.Data;

@Data
public class FilterHolder {
    private Filter filter;
    private String filterClass;

    public FilterHolder(String filterClass) {
        this.filterClass = filterClass;
    }
}
