package com.txm.hospital.service.serviceImpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.txm.hospital.mapper.ArrangeMapper;
import com.txm.hospital.mapper.DoctorMapper;
import com.txm.hospital.pojo.Arrange;
import com.txm.hospital.pojo.Doctor;
import com.txm.hospital.service.DoctorService;
import com.txm.hospital.utils.Md5Util;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

@Service("DoctorService")
public class DoctorServiceImpl implements DoctorService {
    @Resource
    private DoctorMapper doctorMapper;

    @Resource
    private ArrangeMapper arrangeMapper;

    /**
     * 登录数据校验
     * */
    @Override
    public Doctor login(int dId, String dPassword){
        Doctor doctor = this.doctorMapper.selectById(dId);
        String password = Md5Util.getMD5(dPassword);
        if (doctor == null) {
            return null;
        } else {
            if ((doctor.getdPassword()).equals(password)) {
                return doctor;
            }
        }
        return null;
    }
    /**
     * 分页模糊查询所有医护人员信息
     */
    @Override
    public HashMap<String, Object> findAllDoctors(int pageNumber, int size, String query) {
        Page<Doctor> page = new Page<>(pageNumber, size);
        QueryWrapper<Doctor> wrapper = new QueryWrapper<>();
        wrapper.like("d_name", query).orderByDesc("d_state");
        IPage<Doctor> iPage = this.doctorMapper.selectPage(page, wrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", iPage.getTotal());       //总条数
        hashMap.put("pages", iPage.getPages());       //总页数
        hashMap.put("pageNumber", iPage.getCurrent());//当前页
        hashMap.put("doctors", iPage.getRecords()); //查询到的记录
        return hashMap;
    }

    /**
     * 根据id查找医生
     */
    @Override
    public Doctor findDoctor(int dId) {
        return this.doctorMapper.selectById(dId);
    }

    /**
     * 增加医生信息
     */
    @Override
    public Boolean addDoctor(Doctor doctor) {
        //如果账号已存在则返回false
        List<Doctor> doctors = this.doctorMapper.selectList(null);
        for (Doctor doctor1 : doctors) {
            if (doctor.getdId() == doctor1.getdId()) {
                return false;
            }
        }
        //密码加密
        String password = Md5Util.getMD5(doctor.getdPassword());
        doctor.setdPassword(password);
        doctor.setdState(1);
        doctor.setdStar(0.00);
        doctor.setdPeople(0);
        this.doctorMapper.insert(doctor);
        return true;
    }

    /**
     * 删除医生信息
     */
    @Override
    public Boolean deleteDoctor(int dId) {
        Doctor doctor = new Doctor();
        doctor.setdId(dId);
        doctor.setdState(0);
        this.doctorMapper.updateById(doctor);
        return true;
    }

    /**
     * 修改医生信息
     */
    @Override
    public Boolean modifyDoctor(Doctor doctor) {
//        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("d_id", doctor.getDId());
//        this.doctorMapper.update(doctor, queryWrapper);
        int i = this.doctorMapper.updateById(doctor);
        System.out.println("影响行数："+i);
        return true;
    }
    /**
     * 根据科室查询所有医生信息
     */
    @Override
    public HashMap<String, Object> findDoctorBySection(String dSection){
//        HashMap<String, Object> hashMap = new HashMap<>();
//        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("d_section", dSection).eq("d_state", 1);
//        List<Doctor> doctors = this.doctorMapper.selectList(queryWrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("doctors", this.doctorMapper.findDoctorBySection(dSection));
        return map;

    }
    /**
     * 分页根据科室查询所有医生信息
     */
    @Override
    public HashMap<String, Object> findDoctorBySectionPage(int pageNumber, int size, String query, String arrangeDate, String dSection) {
        Page<Doctor> page = new Page<>(pageNumber, size);
        QueryWrapper<Doctor> wrapper = new QueryWrapper<>();
        wrapper.select("d_id", "d_name", "d_gender", "d_post", "d_section").like("d_name", query).eq("d_section", dSection).orderByDesc("d_state");
        IPage<Doctor> iPage = this.doctorMapper.selectPage(page, wrapper);
        List<Doctor> records = iPage.getRecords();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", iPage.getTotal());       //总条数
        hashMap.put("pages", iPage.getPages());       //总页数
        hashMap.put("pageNumber", iPage.getCurrent());//当前页
        hashMap.put("doctors", records); //查询到的记录

        // 查询医生是否已排班
        for (Doctor doctor : records) {
            Arrange arrange = arrangeMapper.selectOne(
                    new QueryWrapper<Arrange>().eq("ar_time", arrangeDate).eq("d_id", doctor.getdId())
            );
            if(arrange != null) {
                doctor.setArrangeId(arrange.getArId());
            }

        }
        return hashMap;
    }

    /**
     * 用户评价
     */
    @Override
    public Boolean updateStar(int dId, Double dStar){

        if(this.doctorMapper.updateStar(dId, dStar))
            return true;
        return false;
    }
    /**
     * 上传Excel导入数据
     */
    @Override
    public Boolean uploadExcel(MultipartFile multipartFile) throws Exception {
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
       List<Doctor> doctors = ExcelImportUtil.importExcel(multipartFile.getInputStream(), Doctor.class, params);
        for (Doctor doctor: doctors){
            doctor.setdPassword(Md5Util.getMD5(doctor.getdPassword()));
            this.addDoctor(doctor);
        }
        return true;
    }
    /**
     * Excel导出数据
     */
    @Override
    public Boolean downloadExcel(HttpServletResponse response) throws IOException {
        List<Doctor> doctors = this.findAll();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), Doctor.class, doctors);
        ServletOutputStream stream = response.getOutputStream();
        response.setHeader("content-disposition", "attachment;fileName="+ URLEncoder.encode("医院医生信息.xlsx", "UTF-8"));
        workbook.write(stream);
        stream.close();
        workbook.close();
        return true;
    }
    /**
     * 查询所有医生不分页
     */
    @Override
    public List<Doctor> findAll(){
        return this.doctorMapper.selectList(null);
    }
}
