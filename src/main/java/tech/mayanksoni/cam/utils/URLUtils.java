package tech.mayanksoni.cam.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class URLUtils {
    public static String encodeParams(Map<String, String> requestMap, String baseUrl) {
        baseUrl = baseUrl + "?";
        return requestMap.keySet().stream().map(key -> key + "=" + encodeParamValue(requestMap.get(key))).collect(Collectors.joining("&", baseUrl, ""));
    }

    private static String encodeParamValue(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    public static String decodeParamValue(String value) {
        return URLDecoder.decode(value, StandardCharsets.UTF_8);
    }
}
