package com.jjr8112.webserver.core.cookie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * cookie格式
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cookie {
    private String key;
    private String value;
}

