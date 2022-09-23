import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;


public class Parser {

    private static Document getPage() throws IOException {
        String url = "https://calendarific.com/holidays/2022/RU";
        Document page = Jsoup.parse(new URL(url), 5000);
        return page;
    }

    public static void main(String[] args) throws IOException {
        Document page = getPage();
        Element table = page.select("table[class=table table-hover table-striped table-bordered holiday-list]").first();
        Elements names = table.select("tr").select("td").select("strong");
        Elements value = table.select("tr").select("td").next("td").next("td");
        LinkedList<String> ar = new LinkedList<>();
        for(Element val: value){
            ar.add(val.text());
            }
        int i=-1;
        System.out.println(ar.get(0));
        for(Element name: names){
            i=i+1;
            System.out.println(name.text() + "   " +"-"+ "   "+  ar.get(i));
        }
   }
    }

