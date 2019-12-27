package com.tiger.springlearning.aspectj;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultEncoreable implements Encoreable {
    @Override
    public void performEncore() {
      log.info("default encoreable");
    }
}
