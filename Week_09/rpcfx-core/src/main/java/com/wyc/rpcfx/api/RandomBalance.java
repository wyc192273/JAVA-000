package com.wyc.rpcfx.api;

import java.util.List;
import java.util.Random;

/**
 * @author yuchen.wu
 * @date 2020-12-26
 */

public class RandomBalance implements LoadBalance {

    @Override
    public Url select(List<Url> urls) throws Exception {
        if (urls.size() == 0) {
            return null;
        }
        if (urls.size() == 1) {
            return urls.get(0);
        }
        Random random = new Random();
        return urls.get(random.nextInt(urls.size()));
    }
}
