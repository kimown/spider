package duokan.service;

import duokan.domain.DuokanBookInfo;
import duokan.mapper.DuokanBookInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by google on 16-6-10.
 */

@Service
public class CrudService {

    @Autowired
    private DuokanBookInfoMapper duokanBookInfoMapper;


    public void crud(){

        List<DuokanBookInfo> duokanBookInfoList = new ArrayList<DuokanBookInfo>();
        DuokanBookInfo duokanBookInfo=new DuokanBookInfo();
        duokanBookInfo.setHref("123href");
        duokanBookInfo.setBookId("123");
        duokanBookInfoList.add(duokanBookInfo);
        duokanBookInfoList.add(duokanBookInfo);
        duokanBookInfoMapper.insertBatch(duokanBookInfoList);

    }
}
