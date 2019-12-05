# mybatis-test
 **mybatis源码研究-2019/12/04**

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

  
***mybatis整体架构***  
> mybatis整体架构分为三层，分别是基础支持层、核心处理层和接口层。
> ![mybatis整体架构图](./mybatis整体架构图02.png "mybatis整体架构图")  

***mybatis思维导图***
> ![mybatis思维导图](./mybatis整体架构图01.png "mybatis思维导图")


# XML基础知识

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
```XML  
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
*		|表示元素出现零次或多次
?		|表示元素出现一次或零次
+		|表示元素出现一次或多次
    
    
