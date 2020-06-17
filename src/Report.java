import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Scanner;


public class Report {
    private String origFail;
    private String xmlFail;
    private String path;
    public String fileTxT = "";
    public FileInputStream fis;
    public InputStreamReader isr;
    public int x;
    Column coll = new Column();
    Page pag = new Page();
    Scanner scanner = new Scanner(System.in);

    public void setI(String origFail){
        this.origFail=origFail;
    }

    public void setK(String xmlFail){
        this.xmlFail=xmlFail;
    }

    public void setPath(String path) throws IOException, SAXException, ParserConfigurationException {
        this.path = path;

    }

    public  Report() throws IOException, SAXException, ParserConfigurationException {
        System.out.println("Введите название исходного файла с расширением");
        setI(scanner.next()) ;
        System.out.println("Введите название xml-файла с расширением");
        setK(scanner.next());
        System.out.println("Укажите путь,по которому сохранится сгенерированный отчет ,пример : C://Users//Валерия//IdeaProjects//ReportGenerator");
        setPath(scanner.next());

        DOMParse domExample = new DOMParse();
        domExample.doParse(xmlFail,coll,pag);

        File file1 = new File(path, "Report.txt");

        try(FileWriter writer = new FileWriter("Report.txt", false))

        {    String text = domExample.getFormattedString();
            writer.write(text);
            writer.write("\r\n");

            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        new FileInputStream(origFail), "UTF-16"));

                String buf;

                while ( ( buf = br.readLine () ) != null )

                    fileTxT += buf + "\r\n";
                System.out.print(fileTxT);
                br.close();


            }catch(IOException IOEx){
                System.out.println(IOEx.toString());
            }

            writer.write("\r\n");
            writer.write(fileTxT);


                writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }


    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        Report report=new Report();
    }

}







