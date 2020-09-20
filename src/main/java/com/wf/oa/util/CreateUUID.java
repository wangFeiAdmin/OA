package com.wf.oa.util;

import java.util.UUID;

public final class CreateUUID {
    private CreateUUID(){}

    /**
     * 生成一个uuid
     * @return
     */
    public static String getUUID(){
        String uuid=""+ UUID.randomUUID();
        return uuid.replace("-","");
    }

}
