package com.common.core.utils;

import java.util.UUID;

public class UUIDUtils {
    public static String randomUUID() {
        String uuid = UUID.randomUUID().toString();
        return replace(uuid);
    }

    private static String replace(String uuid) {
        return uuid.replaceAll("-", "");
    }

}
