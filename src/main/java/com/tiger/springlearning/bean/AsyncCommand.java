package com.tiger.springlearning.bean;

public class AsyncCommand implements Command {

    @Override
    public void execute() {
        System.out.println("执行命令");
    }
}
