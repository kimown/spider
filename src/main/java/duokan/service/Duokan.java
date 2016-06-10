package duokan.service;

import com.alibaba.fastjson.JSON;
import duokan.domain.DuokanBookInfo;
import duokan.mapper.DuokanBookInfoMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by kimown on 2015/11/22.
 */
public class Duokan {
    private static boolean openProxy = true;
    private static String urlTemplate = "http://www.duokan.com/list/6-%s";

    @Autowired
    private static DuokanBookInfoMapper duokanBookInfoMapper;

    /**
     * 开启Fiddler监控模式
     */
    private static void openProxy() {
        if (openProxy == true) {
            System.setProperty("http.proxyHost", "127.0.0.1");
            System.setProperty("http.proxyPort", "8888");
        }
    }

    /**
     * 将原生HTML元素去重无用的数据,同时转换为DuokanBookInfo对象
     *
     * @throws IOException
     */
    public static DuokanBookInfo transform2Bean(Element element) {
        String bookId = element.select(".j-bookitm .book").attr("data-id");
        String href = element.select(".j-bookitm .book a").attr("href");
        String coverURL = element.select(".book .cover img[src^=\"http://cover.read.starter123.com\"]").attr("src");
        String title = element.select(".info .title").html();
        String rawBuyPrice = element.select(".book .u-price em").html();
        String rawOriginalPrice = element.select(".book .u-price del").html();
        String rawStargrade = element.select(".info .u-stargrade div").attr("class");
        String rawRatingValue = element.select(".info .u-stargrade .num").html();
        String author = element.select(".info .u-author span").html();
        String desc = element.select(".info .desc").html();
        //购买价转化为double
        double buyPrice = Double.parseDouble(!rawBuyPrice.equals("") ? rawBuyPrice.split("¥")[1] : "0");
        //原价转化为double
        //有些书没有原价，例如http://www.starter123.com/list/6-2 中的《结网》
        double originalPrice = Double.parseDouble(!rawOriginalPrice.equals("") ? rawOriginalPrice.split("¥")[1] : "0");
        //星级转化为int
        int stargrade = Integer.parseInt(!rawStargrade.equals("") ? rawStargrade.split("icon grade")[1] : "0");
        //排名转化为int
        int ratingValue = Integer.parseInt(rawRatingValue.split(" ")[1]);

        DuokanBookInfo duokanBookInfo = new DuokanBookInfo();
        duokanBookInfo.setBookId(bookId);
        duokanBookInfo.setHref(href);
        duokanBookInfo.setCoverURL(coverURL);
        duokanBookInfo.setTitle(title);
        duokanBookInfo.setBuyPrice(buyPrice);
        duokanBookInfo.setOriginalPrice(originalPrice);
        duokanBookInfo.setStargrade(stargrade);
        duokanBookInfo.setRatingValue(ratingValue);
        duokanBookInfo.setAuthor(author);
        duokanBookInfo.setDesc(desc);
        return duokanBookInfo;
    }


    public static Map<String, List<DuokanBookInfo>> getDocByJSOUP(String url) throws IOException {
        print("正在抓取:%s的数据...", url);
        Document document = Jsoup.connect(url).get();
        Elements element = document.select(".j-bookitm");
        Map<String, List<DuokanBookInfo>> map = new HashMap<String, List<DuokanBookInfo>>();

        List<DuokanBookInfo> duokanBookInfoList = new ArrayList<DuokanBookInfo>();
        for (Element element1 : element) {
            DuokanBookInfo duokanBookInfo = new DuokanBookInfo();
            duokanBookInfo = transform2Bean(element1);
            duokanBookInfoList.add(duokanBookInfo);
        }
        map.put(url, duokanBookInfoList);
        return map;
    }

    /**
     * 格式化输出
     *
     * @param msg
     * @param args
     */
    private static void print(Object msg, Object... args) {
        System.out.println(String.format(msg.toString(), args));
    }

    public static void crawler2Duokan() throws IOException {
        //开启fiddler代理
        //openProxy();
        StringBuffer stringBuffer = new StringBuffer();
        List<DuokanBookInfo> duokanBookInfoList = new ArrayList<DuokanBookInfo>();
        for (int i = 1; i < 5; i++) {
            String url = String.format(urlTemplate, i);
            try {
                Map<String, List<DuokanBookInfo>> map = getDocByJSOUP(url);
                duokanBookInfoList.addAll(map.get(url));
                //避免Gson把一些字符自动转化为Unicode.
//                Gson gson = new GsonBuilder().disableHtmlEscaping().create();
//                String json = gson.toJson(map);
                String json = "";
                print("抓取完毕，对象的JSON数据：%s", json);
                stringBuffer.append(json + "\n");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("抓取出错");
            }
        }

        String jsonArryStr = JSON.toJSONString(duokanBookInfoList);
        List<String> stringList = Arrays.asList(jsonArryStr);
        Path path = Paths.get("tempforSerialize.json");
        Files.write(path, stringList, Charset.forName("UTF-8"));
        List<String> stringList1 = Files.readAllLines(path);

        try {
            int a = duokanBookInfoMapper.insertBatch(duokanBookInfoList);
        }catch (Exception e){
            e.printStackTrace();
        }


//        write2File(stringBuffer.toString());
    }

    /**
     * 将获取的JSON数据持久化
     *
     * @param json
     * @throws IOException
     */
    public static void write2File(String json) throws IOException {
        String path = "./output/starter123" + generateDateStr() + ".txt";
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        long start = System.currentTimeMillis();
        print("-----开始进行字节写入文件，初始时间：%s-----", start);
        fileOutputStream.write(json.getBytes());
        long end = System.currentTimeMillis();
        print("-----字节写入文件执行完毕，结束时间：%s-----", end);
        print("字节写入文件总计花费时间=" + (end - start) + "毫秒");


        long start1 = System.currentTimeMillis();
        print("-----开始进行缓存写入文件，初始时间：%s-----", start1);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + "buf.txt"));
        bufferedWriter.write(json);
        bufferedWriter.flush();
        long end1 = System.currentTimeMillis();
        print("-----缓存写入文件执行完毕，结束时间：%s-----", end1);
        print("缓存写入文件总计花费时间=" + (end1 - start1) + "毫秒");
    }

    /**
     * 将当前时间作为输出文件名
     *
     * @throws IOException
     */
    public static String generateDateStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String date = simpleDateFormat.format(new Date());
        return date;
    }

    public static void main(String[] args) throws IOException {

        Duokan.crawler2Duokan();

    }
}
