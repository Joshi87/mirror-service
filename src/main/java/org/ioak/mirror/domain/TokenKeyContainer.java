package org.ioak.mirror.domain;
import lombok.extern.slf4j.Slf4j;
import org.ioak.mirror.service.RandomString;

import java.util.*;

@Slf4j
public class TokenKeyContainer {
    private static Map<String, TokenKey> tokenMap;
    private static List<TokenKey> tokenList;

    static{
        initialize();
    }

    public static void initialize() {
        tokenMap = new HashMap();
        tokenList = new ArrayList();

        for (int i=0; i<=100; i++) {
            TokenKey tokenKey = new TokenKey(RandomString.nextString(10), RandomString.nextString(20));
            tokenList.add(tokenKey);
            tokenMap.put(tokenKey.getKey(), tokenKey);
        }
    }

    public static TokenKey get() {
        int randomIndex = new Random().nextInt(tokenList.size());
        return tokenList.get(randomIndex);
    }

    public static TokenKey get(String key) {
        return tokenMap.get(key);
    }

}

