package com.tiger.thread;

/**
 * @ClassName Test
 * @Description TODO
 * @Author tiger
 * @Date 2020/1/18 18:26
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
        try{
            new TestA(null);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("==============");
    }
}


class TestA{

    public TestA(String id){
        String[] split = id.split("-");

    }

}