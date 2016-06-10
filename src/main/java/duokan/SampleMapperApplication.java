/**
 * Copyright 2015-2016 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package duokan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import duokan.domain.City3;
import duokan.domain.DuokanBookInfo;
import duokan.mapper.CityMapper;
import duokan.mapper.DuokanBookInfoMapper;
import duokan.service.Duokan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SampleMapperApplication implements CommandLineRunner {

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private DuokanBookInfoMapper duokanBookInfoMapper;

    private static final Logger logger = LoggerFactory
            .getLogger(SampleMapperApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(SampleMapperApplication.class, args);
    }


    public void run(String... args) throws Exception {



//        List<City3> cityList = cityMapper.findAllFromXml();
//        System.out.print(Arrays.toString(cityList.toArray()));

//        Duokan.crawler2Duokan();
        logger.debug("ddd");

        System.out.println("-----------========---------");

//        List<DuokanBookInfo> duokanBookInfoList = new ArrayList<DuokanBookInfo>();
//        DuokanBookInfo duokanBookInfo=new DuokanBookInfo();
//        duokanBookInfo.setHref("123href");
//        duokanBookInfo.setBookId("123");
//        duokanBookInfoList.add(duokanBookInfo);
//        duokanBookInfoMapper.insertBatch(duokanBookInfoList);


    }

}
