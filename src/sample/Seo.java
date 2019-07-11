package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;


public class Seo {
    public static void main(String[] args) {
        int t = 1;

        Scanner scan = new Scanner(System.in);


        while (true) {

            System.out.println("숫자 1에서 3까지 입력해주세요.(종료하려면 0을 입력하세요.)");
            System.out.println(" ");

            int cnt = scan.nextInt();
            if (cnt == 0) {
                System.out.println("종료합니다.");
                break;
            } else if (cnt == 1) {
                try {
                    Document doc = Jsoup.connect("https://www.youtube.com/feed/trending").get();
                    Elements elements = doc.select("div h3");
                    // 이렇게 select()안에 다 써도 됨
                    // Elements elements = doc.select("[data-age=all] div ul li span");
                    List<String> keywords = elements.eachText();

                    System.out.println("<< 유튜브 인기 동영상 순위 >>");
                    System.out.println(" ");
                    writefile("\n<< 유튜브 인기 동영상 순위 >> \n\n");

                    for (int i = 3; i < 13; i++) {
                        System.out.println((i - 2) + ". " + keywords.get(i));
                        System.out.println(" ");

                        writefile((i - 2) + ". " + keywords.get(i) + "\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                continue;
            } else if (cnt == 2) {
                try {
                    Document doc = Jsoup.connect("https://www.melon.com/").get();
                    Elements elements = doc.select("div p");
                    // 이렇게 select()안에 다 써도 됨
                    // Elements elements = doc.select("[data-age=all] div ul li span");
                    List<String> keywords = elements.eachText();

                    System.out.println("<< 멜론 실시간 음악 순위 >>");
                    System.out.println(" ");
                    writefile("\n<< 멜론 실시간 음악 순위 >> \n\n");
                    for (int i = 1; i < 11; i++) {
                        System.out.println((i) + ". " + keywords.get(i));
                        System.out.println(" ");
                        writefile((i - 2) + ". " + keywords.get(i) + "\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                continue;
            } else if (cnt == 3) {
                try {
                    Document doc = Jsoup.connect("https://play.google.com/store/apps/top?hl=ko").get();
                    Elements elements = doc.select("div a div");
                    // 이렇게 select()안에 다 써도 됨
                    // Elements elements = doc.select("[data-age=all] div ul li span");
                    List<String> keywords = elements.eachText();

                    System.out.println("<< PLAYSTORE 인기 앱 순위 >>");
                    System.out.println(" ");
                    writefile("\n<< PLAYSTORE 인기 앱 순위 >> \n\n");
                    for (int i = 0; i < 20; i += 2) {
                        System.out.println((t) + ". " + keywords.get(i));
                        System.out.println(" ");
                        writefile((t) + ". " + keywords.get(i) + "\n");
                        t++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                continue;
            } else {
                System.out.println("다시 입력해주세요.");
                System.out.println(" ");
            }
        }
    }

    public static String Date(){
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy_MM_dd");

        Calendar time = Calendar.getInstance();

        String format_time1 = format1.format(time.getTime());

        return format_time1;
    }

    public static void writefile(String message) {

        String date = Date();
        File file = new File(date + ".txt");
        FileWriter writer = null;

        try {
            writer = new FileWriter(file, true);
            writer.write(message);
            writer.flush();

            System.out.println("DONE");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
