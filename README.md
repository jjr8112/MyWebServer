# MyWebServer

## 项目设想

### nio通信模式

<img src="C:\Users\Joker\Desktop\nio.jpg"  />



## 开发总结：

### 1.servlet生命周期

* 客户端发送请求到服务器端
* 服务器端的servlet容器收到请求后解析
* 服务器创建servlet实例
* 服务器调用init()方法完成对实例的初始化
* 服务器调用具体的service()方法
* servlet返回相应的响应信息(动态资源)
* 服务器将响应信息发送至客户端
* 之后servlet容器调用destroy()方法将servlet实例销毁

### 2.服务器工作方式

服务器在工作时应具体分为**两部分**，一部分用于开启Socket、监听客户端发来的请求、返回响应数据，另一部分负责具体的请求处理(即tomcat中的Connector和Container的功能划分)

### 3.Java线程池

![](C:\Users\Joker\Desktop\20180530120605822.png)

### 4.Java多线程--[Java多线程（基于实现Runnable接口方式实现）](https://www.cnblogs.com/jyn66/p/8962790.html)

创建线程**步骤**：

* 定义类，并实现Runnable接口

* 重写Runnable接口中的run()方法

* 通过Thread类建立线程对象

* 将实现了Runnable接口的类作为参数传入Thread类的构造函数

* 调用Thread类对象的start()方法，开始调用Runnable子类的run()方法

补充--**使用Runnable优点**：

> 实现Runnable类和继承Thread的区别：
>
> java可以多实现，但是只能单继承。
>
> 所以实现Runnable对类的影响不大，继承了Thread后就不能在继承其他类。
>
> 其实Runnable接口的出现就是解决继承一个类后，还需要继承Thread实现多线程，但是java不支持多继承，由此使用接口进行扩展。
>
> 所以**基本使用实现Runnable接口创建线程**

### 5. [java并发编程：Executor、Executors、ExecutorService](https://blog.csdn.net/weixin_40304387/article/details/80508236)



### 6.tomcat工作示意图

<img src="C:\Users\Joker\Desktop\tomcat.jpg" style="zoom:200%;" />

## 问题

1.NioPoller的processSocket方法看不太懂

2.NioPoller的getSelector方法及相关操作看不太懂

3.nio通信模型