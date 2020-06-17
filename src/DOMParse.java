import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DOMParse {

    private String formattedString;

    public String getFormattedString(){
        return formattedString;
    }

    public void setFormattedString(String formattedString){
        this.formattedString=formattedString;
    }

    public void doParse(String k, Column coll, Page pag) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document document = null;
        try {
            document = builder.parse(new File(k));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NodeList pageNodeList = document.getElementsByTagName("page");
        Element pageElement = (Element) pageNodeList.item(0);


        NodeList childNodes = pageElement.getChildNodes();
        for (int l = 0; l <= childNodes.getLength()-1; l++) {
            if (childNodes.item(l).getNodeType() == Node.ELEMENT_NODE) {
                Element childElement1 = (Element) childNodes.item(l);

                switch (childElement1.getNodeName()) {
                    case "width": {
                        pag.setPageWidth(Integer.valueOf(childElement1.getTextContent()));
                        System.out.println(pag.getPageWidth1());
                    }
                    break;

                    case "height": {
                        pag.setPageHeight((Integer.valueOf(childElement1.getTextContent())));
                        System.out.println(pag.getPageHeight());
                        break;
                    }

                }

            }

        }

        NodeList columnNodeList = document.getElementsByTagName("column");

        ArrayList<Column> columnList = new ArrayList<>();


        for (int i = 0; i < columnNodeList.getLength(); i++) {


            if (columnNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element columnElement = (Element) columnNodeList.item(i);

                Column column = new Column();
                NodeList childNodes1 = columnElement.getChildNodes();
                for (int o = 0; o < childNodes.getLength(); o++) {
                    if (childNodes1.item(o).getNodeType() == Node.ELEMENT_NODE) {
                        Element childElement = (Element) childNodes1.item(o);

                        switch (childElement.getNodeName()) {
                            case "title": {
                                column.setTitle(childElement.getTextContent());
                                System.out.println(column.getTitle());
                            }
                            break;

                            case "width": {
                                column.setWidth((Integer.valueOf(childElement.getTextContent())));
                                System.out.println(column.getWidth());
                            }

                        }

                    }

                }
                columnList.add(column);



            }
        }

         setFormattedString(columnList.toString()
                 .replace(",", "")  //remove the commas
                 .replace("[", "")  //remove the right bracket
                 .replace("]", "")  //remove the left bracket
                 .trim());
        System.out.println(getFormattedString());
    }
}
