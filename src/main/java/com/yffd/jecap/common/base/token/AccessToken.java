package com.yffd.jecap.common.base.token;

import lombok.Getter;

@Getter
public class AccessToken implements IToken {
    private static final long serialVersionUID = 6570390971872526843L;
    private String tokenId;
    private int expireTime;

    public AccessToken(String tokenId) {
        this.tokenId = tokenId;
        this.expireTime = -1;
    }

    public AccessToken(String tokenId, int expireTime) {
        this.tokenId = tokenId;
        this.expireTime = expireTime;
    }

}
