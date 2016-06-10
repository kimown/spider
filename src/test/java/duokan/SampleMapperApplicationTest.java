package duokan;


import com.alibaba.fastjson.TypeReference;
import duokan.domain.DuokanBookInfo;
import duokan.mapper.DuokanBookInfoMapper;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import com.alibaba.fastjson.JSON;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.transaction.annotation.Transactional;
import sun.nio.cs.StandardCharsets;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kimown on 16-6-4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SampleMapperApplication.class)
public class SampleMapperApplicationTest {


    @Autowired
    private DuokanBookInfoMapper duokanBookInfoMapper;

    /**
     * ref: https://m.oschina.net/blog/373685
     */
    @Test
    public void testSerializeForList() throws IOException {
        List<DuokanBookInfo> duokanBookInfoList = new ArrayList<DuokanBookInfo>();
        DuokanBookInfo duokanBookInfo = new DuokanBookInfo();
        duokanBookInfo.setBookId("123");
        duokanBookInfo.setHref("456href");
        DuokanBookInfo duokanBookInfo1 = new DuokanBookInfo();
        duokanBookInfo1.setBookId("123====");
        duokanBookInfo1.setHref("456href====");
        duokanBookInfoList.add(duokanBookInfo);
        duokanBookInfoList.add(duokanBookInfo1);
        String jsonArryStr = JSON.toJSONString(duokanBookInfoList);
        System.out.println(jsonArryStr);

        List<String> stringList = Arrays.asList(jsonArryStr);
        Path path = Paths.get("temp.json");
        Files.write(path, stringList,Charset.forName("UTF-8"));
        List<String> stringList1 = Files.readAllLines(path);
        List<DuokanBookInfo> empList = JSON.parseObject(stringList1.get(0), new TypeReference<List<DuokanBookInfo>>(){} );
    }

    @Test
    public void testFileRW() throws IOException {
        String s="12312312";
        List<String> lines = Arrays.asList("The first line", "The second line");
        Path file = Paths.get("the-file-name.txt");
        Files.write(file, lines, Charset.forName("UTF-8"));
        List<String>  list= Files.readAllLines(file);
    }

    @Test
    public void testMybatis() throws IOException {
        Path path = Paths.get("tempforSerialize1.json");
        List<String> stringList1 = Files.readAllLines(path);
        List<DuokanBookInfo> empList = JSON.parseObject(stringList1.get(0), new TypeReference<List<DuokanBookInfo>>(){} );
        duokanBookInfoMapper.insertBatch(empList);

    }

    @Test
    public void testFakeInsert() {
        DuokanBookInfo duokanBookInfo = new DuokanBookInfo();
        duokanBookInfo.setBookId("baidu123");
        List<DuokanBookInfo> duokanBookInfoList = new ArrayList<DuokanBookInfo>();
        duokanBookInfoList.add(duokanBookInfo);
        duokanBookInfoList.add(duokanBookInfo);
        duokanBookInfoList.add(duokanBookInfo);
//        for(DuokanBookInfo duokanBookInfo1:duokanBookInfoList){
//            List<DuokanBookInfo> duokanBookInfoList1 = new ArrayList<DuokanBookInfo>();
//            duokanBookInfoList1.add(duokanBookInfo1);
//            duokanBookInfoMapper.insertBatch(duokanBookInfoList);
//        }
        duokanBookInfoMapper.insertBatch(duokanBookInfoList);


    }

    @Test
    public void testMaven () {


    }

}
