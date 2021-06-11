package athena.springboot.starter.marian.service;

import athena.springboot.starter.marian.po.PlatformPo;

public class PlatformService {

    public PlatformPo getPlatfrom() {
        PlatformPo platform = new PlatformPo();
        platform.setDesc("via");
        platform.setRound(3);
        return platform;
    }

}
