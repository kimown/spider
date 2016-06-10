package duokan.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import duokan.domain.DuokanBookInfo;
import duokan.mapper.DuokanBookInfoMapper;
import duokan.service.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kimown on 16-6-4.
 */
@Controller
public class SampleController {

    @Autowired
    private CrudService crudService;

    private static final Logger logger = LoggerFactory
            .getLogger(SampleController.class);

    @RequestMapping("/")
    @ResponseBody
    String home()  {



        Path path = Paths.get("tempforSerialize.json");
        List<String> stringList1 = null;
        try {
            stringList1 = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<DuokanBookInfo> empList = JSON.parseObject(stringList1.get(0), new TypeReference<List<DuokanBookInfo>>(){} );
        crudService.crud();

        return "Hello World!";
    }

}
