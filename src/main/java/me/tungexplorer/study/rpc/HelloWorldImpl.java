package me.tungexplorer.study.rpc;

import javax.jws.WebService; 

@WebService(endpointInterface = "rpc_helloworld.HelloWorld")  
public class HelloWorldImpl implements HelloWorld{

    @Override
    public String getHelloWorld(String name) {
        return name;
    }
}