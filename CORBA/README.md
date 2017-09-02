2017/9/1

CORBA两个项目，一是打印hello；二是四则运算calc。

以hello项目为例，流程分七步，如下：
## 一、定义IDL
#hello.idl
```
module helloworld{
   interface HelloWorld{
      string sayHello();
   };
};
```


## 二、根据IDL生成存根和代理等代码
```
$ idlj -fall Hello.idl
```
说明：运行后，hello.idl目录下自动生成helloworld文件夹，文件夹中会有6个文件，
* 客户端必需：_HelloWorldStub.java、HelloWorldHelper.java、HelloWorldHolder.java
* 服务端必需： HelloWorldPOA.java
* C/S都需要：HelloWorldOperations.java、HelloWorld.java。

## 三、开发CORBA服务: xxxServer
// 服务端实现服务
```
class HelloWorldImpl extends HelloWorldPOA {
    public String sayHello() {
        return "Hello World!";
    }
}

public class HelloServer {
    public static void main(String[] args){
        //创建一个ORB实例
        ORB orb = ORB.init(args, null);

        //拿到RootPOA的引用，并激活POAManager，相当于启动了server
        org.omg.CORBA.Object obj=orb.resolve_initial_references("RootPOA");
        POA rootpoa = POAHelper.narrow(obj);
        rootpoa.the_POAManager().activate();

        //创建一个HelloWorldImpl实例
        HelloWorldImpl helloImpl = new HelloWorldImpl();

        //从服务中得到对象的引用，并注册到服务中
        org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
        HelloWorld href = HelloWorldHelper.narrow(ref);

        //得到一个根名称的上下文
        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

        //在命名上下文中绑定这个对象
        String name = "Hello";
        NameComponent path[] = ncRef.to_name(name);
        ncRef.rebind(path, href);

        //启动线程服务，等待客户端调用
        orb.run();
	}
}
```

## 四、开发客户端：xxxClient
```
public class HelloClient{
    public static void main(String[] args){
        //创建一个ORB实例
        ORB orb = ORB.init(args, null);

        //获取根名称上下文
        org.omg.CORBA.Object obj=orb.resolve_initial_references("NameService");
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

		//从命名上下文中获取接口实现对象
		calcImpl = CalcHelper.narrow(ncRef.resolve_str("Calc"));

		//调用接口对象的方法
		System.out.println("Obtained a handle on server object: " + calcImpl);}
		IntHolder add_val = new IntHolder();
		calcImpl.add(1, 2, add_val);
	}
}
```

## 五、启动CORBA服务器
```
# 先编译所有的Java文件：
$ javac -Xlint:unchecked -d . *.java

# 运行：orbd -ORBInitiaPort [] -ORBInitiaHost []
$ orbd -ORBInitialPort 1050 -ORBInitialHost 127.0.0.1
```

## 六、开启CORBA服务
```
$ java xxxServer -ORBInitiaPort []
```

## 七、启动CORBA客户端
```
$ java xxxClient -ORBInitiaHost [] -ORBInitiaPort []
```
