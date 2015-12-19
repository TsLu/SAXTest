package demo;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
//import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
//import org.xml.sax.helpers.DefaultHandler;


/**
 * 使用SAX解析xml文档
 * Created by luts on 2015/12/18.
 */
public class SAXTest {
    public static void main(String[] args){
        //创建SAXParserFactory实例、
        SAXParserFactory factory = SAXParserFactory.newInstance();
        String xmlPath = "src/books.xml";
        try {
            SAXParser SAXparser = factory.newSAXParser();

            //获取事件源
            XMLReader xmlReader = SAXparser.getXMLReader();
            //设置处理器,创建一个SAXParserHandler
            BookSAXHandler handler = new BookSAXHandler();

            InputStream inputStream = new FileInputStream(new File(xmlPath));

            SAXparser.parse("src/books.xml", handler);
            System.out.println("----开始解析某一本书的" + handler.getBookList().size()
                    + " 个属性-------");
            for (Books book : handler.getBookList()){
                System.out.println(book.getCategory());
                System.out.println(book.getTitle());
                System.out.println(book.getLanguage());
                System.out.println(book.getAuthor());
                System.out.println(book.getYear());
                System.out.println(book.getPrice());
                System.out.println("----结束属性解析-----");
            }
        }
        catch (ParserConfigurationException e){
            e.printStackTrace();
        }
        catch (SAXException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }



}
