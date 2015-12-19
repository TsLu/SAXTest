package demo;

import jdk.internal.org.xml.sax.Attributes;
import jdk.internal.org.xml.sax.SAXException;
import jdk.internal.org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by luts on 2015/12/18.
 */
public class BookSAXHandler extends DefaultHandler {
    String value = null;
    Books book = null;
    private ArrayList<Books> bookList = new ArrayList<Books>();
    public ArrayList<Books> getBookList(){
        return bookList;
    }


    int bookIndex = 0;

    //标识解析开始
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("SAX解析开始");

    }

    //标识解析结束
    public void endDocument() throws SAXException{
        super.endDocument();
        System.out.println("SAX解析结束");
    }

    //解析xml元素
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
        //调用DefualtHandler类的startElement方法
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals("book")){
            bookIndex++;
            book = new Books(); //创建一个book对象
            System.out.println("-----------开始遍历某一本书的内容----------");

            // 已知book元素下属性的名称，根据属性名获取属性值
           /* String value = attributes.getValue("category");
            System.out.println("Book的属性值是" + value);*/

            //不知道book元素下属性的名称和个数，如何获取属性名以及属性值
            int num = attributes.getLength();
            for (int i = 0; i < num; i++){
                System.out.print("book元素的第" + (i + 1) + "个属性名是： " + attributes.getQName(i));
                System.out.println("---属性值是： " + attributes.getValue(i));
                if (attributes.getQName(i).equals("category")){
                    book.setCategory(attributes.getValue(i));
                }

                if (attributes.getQName(i).equals("lang")){

                    book.setLanguage(attributes.getValue(i));
                }
            }

        }
        if (qName.equals("title")){
            // 已知title元素下属性的名称，根据属性名获取属性值
            String lang = attributes.getValue("category");
            System.out.println("Title的属性值是" + lang);
            book.setLanguage(lang);
        }

        else if (!qName.equals("title") && !qName.equals("bookstore")){
            System.out.print("节点名是： " + qName + "----");
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException{
        //调用DefaulyHandler类的endElement方法
        super.endElement(uri, localName, qName);

        //判断是否针对一本书以及遍历完毕
        if (qName.equals("book")){
            bookList.add(book);
            book = null;
            System.out.println("---------结束遍历某一本的内容-----------");
        }
        else if (qName.equals("title")){
            book.setTitle(value);
        }
        else if (qName.equals("author")){
            book.setAuthor(value);
        }
        else if (qName.equals("year")){
            book.setYear(value);
        }
        else if (qName.equals("price")){
            book.setPrice(value);
        }
    }

    public void cahracters(char[] ch, int start, int length) throws SAXException{
        super.characters(ch, start, length);
        value = new String(ch, start, length);
        if (!value.trim().equals("")){
            System.out.println("节点值是：" + value);
        }
    }
}
