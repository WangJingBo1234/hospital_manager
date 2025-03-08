package com.txm.hospital.service.serviceImpl;

import com.txm.hospital.mapper.ArrangeMapper;
import com.txm.hospital.pojo.Arrange;
import com.txm.hospital.service.ArrangeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;

@Service("ArrangeService")
public class ArrangeServiceImpl implements ArrangeService {
    @Autowired
    private ArrangeMapper arrangeMapper;
    @Autowired
    private JedisPool jedisPool;//redis连接池

    /**
     * 根据日期查询排班信息
     */
    @Override
    public List<Arrange> findByTime(String arTime, String dSection) {
        return this.arrangeMapper.findByTime(arTime, dSection);
    }
    /**
     * 增加排班信息
     */
    public Boolean addArrange(Arrange arrange){
        Arrange arrange1 = this.arrangeMapper.selectById(arrange.getArId());
        Jedis jedis = jedisPool.getResource();
        HashMap<String, String> map = new HashMap<>();
        map.put("eTOn","40");
        map.put("nTOt","40");
        map.put("tTOe","40");
        map.put("fTOf","40");
        map.put("fTOs","40");
        map.put("sTOs","40");
        if (arrange1 == null) {
            //redis操作开始
//            jedis.hset(arrange.getArId(), map);
            // 或者使用hmset设置整个哈希表的值
            jedis.hmset(arrange.getArId(), map);
            jedis.expire(arrange.getArId(), 604800);
            //redis操作结束
            this.arrangeMapper.insert(arrange);
            return true;
        }
        return false;
    }

    /**
     * 删除排班信息
     */
    public Boolean deleteArrange(String arId){
        Arrange arrange = this.arrangeMapper.selectById(arId);
        Jedis jedis = jedisPool.getResource();
        if (arrange != null) {
            jedis.del(arId);
            this.arrangeMapper.deleteById(arId);
            return true;
        }
        return false;
    }

}
