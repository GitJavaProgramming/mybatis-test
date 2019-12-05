package org.pp.mybatis.foundationsupportlayer.parser;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;

public class XPathTest {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        // 参考 javadoc -- javax.xml.parsers.*
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        // 创建的解析器使用DTD验证 如果需要schema验证就指定false 然后使用{@link #setSchema(Schema)}
        documentBuilderFactory.setValidating(true);
        // 创建的解析器不支持XML命名空间
        documentBuilderFactory.setNamespaceAware(false);
        // 创建的解析器忽略注释
        documentBuilderFactory.setIgnoringComments(true);
        // 创建的解析器解析xml文档时是否删除元素内容中的空格
        documentBuilderFactory.setIgnoringElementContentWhitespace(false);
        // 解析器是否将CDATA节点转换为Text节点，并把它附加到相邻的Text节点
        documentBuilderFactory.setCoalescing(false);
        // 生成的解析器将扩展实体引用节点
        documentBuilderFactory.setExpandEntityReferences(true);

        // 创建DocumentBuilder 从而可以获取DOM文档实例
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        // 将文档加载到一个Document对象中
        Document doc = builder.parse("src/main/java/org/pp/mybatis/foundationsupportlayer/parser/inventory.xml");

        XPathFactory xPathFactory = XPathFactory.newInstance();
        // XPath 是一门在 XML 文档中查找信息的语言。XPath 可用来在 XML 文档中对元素和属性进行遍历。
        // XPath 是 W3C XSLT 标准的主要元素，并且 XQuery 和 XPointer 都构建于 XPath 表达之上。
        // 在 XPath 中，有七种类型的节点：元素、属性、文本、命名空间、处理指令、注释以及文档节点（或称为根节点）。
        XPath xPath = xPathFactory.newXPath();

        // 编译XPath表达式 --- FAQ 请自己查找答案
        XPathExpression expr = xPath.compile("//book[author='Neal Stephenson']/title/text()");
        Object result = expr.evaluate(doc, XPathConstants.NODESET/* {@link XPathConstants QName} */);
        System.out.println("查询作者位Neal Stephenson的图书的标题：");
        NodeList nodeList = (NodeList) result;
        printNodeList(nodeList);

        System.out.println("查询1997年之后的图书的标题");
        nodeList = (NodeList) (xPath.evaluate("//book[@year>1997]/title/text()", doc, XPathConstants.NODESET));
        printNodeList(nodeList);

        System.out.println("查询1997年之后的图书的属性和标题");
        nodeList = (NodeList) (xPath.evaluate("//book[@year>1997]/@*|//book[@year>1997]/title/text()", doc, XPathConstants.NODESET));
        printNodeList(nodeList);

    }

    private static void printNodeList(NodeList nodeList) {
        int len = nodeList.getLength();
        for (int i = 0; i < len; i++) {
            System.out.println(nodeList.item(i).getNodeValue());
        }
    }
}
