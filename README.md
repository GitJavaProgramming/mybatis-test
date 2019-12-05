<!-- TOC -->autoauto- [mybatis-test](#mybatis-test)auto- [XML基础知识（名称空间/文档验证/文档处理）](#xml基础知识名称空间文档验证文档处理)auto    - [XML](#xml)auto    - [XML名称空间](#xml名称空间)auto    - [DTD(Document Type Definition)](#dtddocument-type-definition)auto        - [系统标识符：](#系统标识符)auto        - [公共标识符：](#公共标识符)auto        - [DTD详述](#dtd详述)auto            - [元素声明](#元素声明)auto                - [一个元素内容模型定义了可允许的元素内容。](#一个元素内容模型定义了可允许的元素内容)auto                - [基数是指这个元素在内容模型中出现的次数。DTD有4个基数指示符。](#基数是指这个元素在内容模型中出现的次数dtd有4个基数指示符)auto            - [属性声明](#属性声明)auto    - [schema](#schema)autoauto<!-- /TOC -->
# mybatis-test
 **mybatis源码研究-2019/12/04**

****

***mybatis整体架构***  

mybatis整体架构分为三层，分别是基础支持层、核心处理层和接口层。  
> ![mybatis整体架构图](./mybatis整体架构图02.png "mybatis整体架构图")    

****
# XML基础知识（名称空间/文档验证/文档处理）

## XML
所有的 XML 文档（以及 HTML 文档）均由以下简单的构建模块构成：  

>   元素  
>   属性  
>   实体  
>   PCDATA  
>   CDATA   

## XML名称空间  
名称空间为了避免条目命名冲突  

## DTD(Document Type Definition)  

参考资料：
>  [XML入门经典(第5版)  第I部分-->第III部分](https://pan.baidu.com/s/1M3HSfL3VQgpVvHa_ekUjvQ "提取码是9mfm")

文档类型声明或DOCTYPE告诉解析器，XML文档必须遵循DTD定义。同时它也告诉解析器，到哪里找到文档定义的其余内容。  
`<!DOCTYPE name [ ]>`

引用外部DTD要用下面两种方法之一：系统标识符和公共标识符。

### 系统标识符：  
关键字SYSTEM和指向文档位置的URI引用。URI可以是硬盘上的一个文件，也可以是局域网或者internet上的一个文件。  
`<!DOCTYPE name SYSTEM "name.dtd" [...]>`

### 公共标识符：  
以PUBLIC关键字开始，其后紧跟一个专用的标识符，但是公共标识不是用来表示对文件的引用，而是表示目录中的一个记录。
根据XML规范，公共标识符可以采用任何格式，但是一种经常使用的格式是正式公共标识符(Formal Public Identifier, FPI)。
>	FPI的语法要匹配下面的基本结构：  
>	    -//Owner//Class Description//Language//Version  
>   从底层的角度看，它与名称空间的作用相似，但是公共标识符不能把两个不同的词汇组合到同一个文档里。就因为这一点，名称空间比它功能更强大。

在标识符字符串之后，还可以插入一个可选的系统标识符。这样，当处理器不能解析公共标识符时，可以查找这个文档的副本（大多数处理器不能解析公共标识符）。  
`<!DOCTYPE name PUBLIC "-//Beginning XML//DTD Name Example//EN" "name.dtd">`

### DTD详述
通常，DTD由三个基本部分组成：  
* 元素声明  
* 属性声明  
* 实体声明  

#### 元素声明
由三个部分组成：ELEMENT声明 元素名 元素内容模型
``` XML  
<!ELEMENT name (first, middle, last)>
```
ELEMENT声明告诉解析器当前声明一个元素。
##### 一个元素内容模型定义了可允许的元素内容。  
就XML标准而言，有四类内容文档。（FAQ. 重点，请自行查阅相关资料）  
* 元素内容
* 混合内容
* 空内容
* 任意内容
##### 基数是指这个元素在内容模型中出现的次数。DTD有4个基数指示符。

指示符	|	说明
--------|----------------
[none]	|内容模型中默认方式，表示这个元素必须且只出现一次
`?`		|表示元素出现一次或零次
`+`		|表示元素出现一次或多次
`*`		|表示元素出现零次或多次

#### 属性声明    
属性声明需要使用ATTLIST关键字。  
ATTLIST包含三个部分：ATTLIST关键字 相应元素名 属性列表  
``` xml
<!ELEMENT book (title, author*, publisher,isbn,price)>
<!ATTLIST book year CDATA #IMPLIED>
```
上面例子指出book元素包含year属性，CDATA用来定义属性类型，#IMPLIED指出该属性不是必须的。  
`ref:  https://www.w3school.com.cn/dtd/dtd_attributes.asp`

## schema
XML Schema 参考手册：  
> https://www.w3school.com.cn/schema/schema_elements_ref.asp

            XML Schema 是基于 XML 的 DTD 替代者。XML Schema也称为XML框架或XML模式。通过Schema可以描述和规范XML文档的数据模式和组织结构，规定  
        XML文档中可以包含哪些元素、这些元素拥有哪些子元素及其出现的顺序和次数，还规定每个元素和属性的数据类型。与DTD相比，通过XML Schema可以  
        更好地规范和验证有效的XML文档。
            XML Schema总是以独立文档形式存在，其文件扩展名为.xsd。XML Schema文档本身就是一个符合XML规范的、格式良好的XML文档，该文档通过一套预先
        定义的XML元素及其属性创建的，正是这些特定的元素和属性规定了XML文档的结构和内容模式。

Schema文档从一个XML文档声明开始，其后的内容是对根元素schema的声明。根据需要，还可以从一个Schema文档中包含或导入其他Schema文档。  
eg. http://www.springframework.org/schema/beans/spring-beans-4.1.xsd  文档基本框架如下：
```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<xsd:schema xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsd="http://www.w3.org/2001/XMLSchema"
		targetNamespace="http://www.springframework.org/schema/beans">

	<xsd:import namespace="http://www.w3.org/XML/1998/namespace"/>
    <!--元素和属性声明-->
</xsd:schema>
```
    xmlns：指定该schema的默认名称空间
    targetNamespace：声明了在该xsd文档下定义的元素属于该命名空间
    schemaLocation：告诉验证器去哪里找到可用于验证的XML Schema文档

举个栗子：  
```java
    /** Default Servlet name used by Tomcat, Jetty, JBoss, and GlassFish */
	private static final String COMMON_DEFAULT_SERVLET_NAME = "default";
	/** Default Servlet name used by Google App Engine */
	private static final String GAE_DEFAULT_SERVLET_NAME = "_ah_default";
	/** Default Servlet name used by Resin */
	private static final String RESIN_DEFAULT_SERVLET_NAME = "resin-file";
	/** Default Servlet name used by WebLogic */
	private static final String WEBLOGIC_DEFAULT_SERVLET_NAME = "FileServlet";
	/** Default Servlet name used by WebSphere */
	private static final String WEBSPHERE_DEFAULT_SERVLET_NAME = "SimpleFileServlet";
``` 
如果web.xml中配置了某个servlet拦截了所有的请求，那么一些静态资源的访问如jpg，css，js也会被处理。      
```xml
<servlet-mapping>     
    <servlet-name>default</servlet-name>    
    <url-pattern>*.jpg</url-pattern>       
</servlet-mapping>      
<servlet-mapping>         
    <servlet-name>default</servlet-name>      
    <url-pattern>*.js</url-pattern>      
</servlet-mapping>      
<servlet-mapping>          
    <servlet-name>default</servlet-name>         
    <url-pattern>*.css</url-pattern>        
</servlet-mapping>
```
在springMVC-servlet.xml中配置：<mvc:default-servlet-handler/> 这个元素声明在：http://www.springframework.org/schema/mvc/spring-mvc.xsd(FAQ. 自动匹配xsd版本?)
```xml
<xsd:element name="default-servlet-handler">
		<xsd:annotation>
			<xsd:documentation
				source="java:org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler"><![CDATA[
	Configures a handler for serving static resources by forwarding to the Servlet container's default Servlet.  Use of this
	handler allows using a "/" mapping with the DispatcherServlet while still utilizing the Servlet container to serve static
	resources.
	This handler will forward all requests to the default Servlet. Therefore it is important that it remains last in the
	order of all other URL HandlerMappings. That will be the case if you use the "annotation-driven" element or alternatively
	if you are setting up your customized HandlerMapping instance be sure to set its "order" property to a value lower than
	that of the DefaultServletHttpRequestHandler, which is Integer.MAX_VALUE.
			]]></xsd:documentation>
		</xsd:annotation>
		<xsd:complexType>
			<xsd:attribute name="default-servlet-name" type="xsd:string">
				<xsd:annotation>
					<xsd:documentation><![CDATA[
	The name of the default Servlet to forward to for static resource requests.  The handler will try to auto-detect the container's
	default Servlet at startup time using a list of known names.  If the default Servlet cannot be detected because of using an unknown
	container or because it has been manually configured, the servlet name must be set explicitly.
					]]></xsd:documentation>
				</xsd:annotation>
			</xsd:attribute>
		</xsd:complexType>
	</xsd:element>
```
删除文档注释，可以看到*复合类型元素default-servlet-handler*包含一个*default-servlet-name属性*，通过转发请求到Servlet容器的default-servlet从而达到对静态资源的处理。  
（具体实现请参考，笔者版本spring4.1.9：org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler）  
```xml
<!--通过转发到Servlet容器的默认Servlet来配置用于提供静态资源的处理程序。-->
<xsd:element name="default-servlet-handler">
		<xsd:complexType>
			<xsd:attribute name="default-servlet-name" type="xsd:string">
			</xsd:attribute>
		</xsd:complexType>
	</xsd:element>
```
最后介绍一个xml/dtd转xsd工具[trang](https://pan.baidu.com/s/1BEhMthozcXUCnMRKvxi1pg "验证码：yc2a")
```dos
cd C:\Users\45554\Downloads\trang-20030619
java -jar .\trang.jar .\inventory.dtd inventory.xsd
```
FAQ. 自行比较DTD与XML schema  
FAQ. 阅读其他主题XPath、xslt、DOM、Java与xml等  
****

***mybatis思维导图***  
> ![mybatis思维导图](./mybatis整体架构图01.png "mybatis思维导图")  

***主要参考资料：***  
> - [x] [MyBatis技术内幕  徐郡明  2017/07](https://pan.baidu.com/s/1-JGtoXADDjQRw5v51np4vA "提取码是fcak")  
> - [x] [MyBatis 3 源码深度解析  江荣波  2019/10](https://pan.baidu.com/s/1-JGtoXADDjQRw5v51np4vA "最新出版没有电子书")  
> - [x] [MyBatis从入门到精通  刘增辉  2017/07](https://pan.baidu.com/s/1-JGtoXADDjQRw5v51np4vA "提取码是fcak")   
> - [x] [深入浅出MyBatis 技术原理与实战  杨开振  2016/09](https://pan.baidu.com/s/1-JGtoXADDjQRw5v51np4vA "提取码是fcak")  
> - [x] [xml](https://www.w3school.com.cn/xml/index.asp "W3CSchool") 
> - [x] [dtd](https://www.w3school.com.cn/dtd/dtd_entities.asp "W3CSchool") 
> - [x] [schema](https://www.w3school.com.cn/schema/index.asp "W3CSchool") 
> - [x] [xpath](https://www.w3school.com.cn/xpath/xpath_nodes.asp "W3CSchool") 
> - [x] [XML入门经典(第5版)  第I部分-->第III部分](https://pan.baidu.com/s/1M3HSfL3VQgpVvHa_ekUjvQ "提取码是9mfm")  
> - [x] [疯狂XML讲义](https://pan.baidu.com/s/1M3HSfL3VQgpVvHa_ekUjvQ "提取码是9mfm")  
> - [x] [XML揭秘 入门·应用·精通 Michael Morrison 陆新年](https://pan.baidu.com/s/1M3HSfL3VQgpVvHa_ekUjvQ "提取码是9mfm")  
> - [x] [《XML简明教程》2009年清华大学出版社出版 张欣毅](https://www.baidu.com "xml基础简明教程，没找到电子版。")  
> - [x] [Java与XML](https://www.baidu.com "java访问xml")  

