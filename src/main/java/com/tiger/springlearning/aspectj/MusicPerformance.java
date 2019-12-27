package com.tiger.springlearning.aspectj;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MusicPerformance implements Performance {
    @Override
    public void perform() {
      log.info("play piano....");
    }
}
