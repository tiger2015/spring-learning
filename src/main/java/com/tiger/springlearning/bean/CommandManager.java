package com.tiger.springlearning.bean;

public abstract class CommandManager {

    public void process() {
        Command command = createCommand();
        command.execute();
    }

    protected abstract Command createCommand();
}
