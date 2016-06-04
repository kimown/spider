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

import duokan.domain.City3;
import duokan.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SampleMapperApplication implements CommandLineRunner {

    @Autowired
    private CityMapper cityMapper;


    public static void main(String[] args) {
        SpringApplication.run(SampleMapperApplication.class, args);
    }


    public void run(String... args) throws Exception {
        System.out.println(this.cityMapper.findByState("33"));


        List<City3> cityList = cityMapper.findAllFromXml();
        System.out.print(Arrays.toString(cityList.toArray()));
    }

}
