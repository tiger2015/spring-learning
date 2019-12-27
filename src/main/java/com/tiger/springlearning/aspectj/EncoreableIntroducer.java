package com.tiger.springlearning.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class EncoreableIntroducer {
    @DeclareParents(value = "com.tiger.springlearning.aspectj.Performance+", defaultImpl=DefaultEncoreable.class)
    public static Encoreable encoreable;
}
