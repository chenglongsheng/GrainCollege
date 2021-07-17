package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.entity.subject.TwoSubject;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author ChengLongsheng
 * @since 2021-07-17
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    // 添加课程分类
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            // 文件输入流
            InputStream is = file.getInputStream();
            // 调用方法读取
            EasyExcel.read(is, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 课程分类列表（树形）
    @Override
    public List<OneSubject> getAllOneAndTwoSubject() {

        // 查询所有一级分类（parent_id=0）
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        // 查询所有二级分类（parent_id!=0）
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", "0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        //
        List<OneSubject> finalSubjectList = new ArrayList<>();

        // 封装一级分类数据
        /*
        查询出来所有的一级分类list集合遍历，得到每一个一级分类对象，获取其值
        封装到要求的list集合里面 List<OneSubject> finalSubjectList
         */
        for (EduSubject eduSubject : oneSubjectList) {
            //得到每个EduSubject对象
            OneSubject oneSubject = new OneSubject();
            // 把EduSubject中的值放到OneSubject中
//            oneSubject.setId(eduSubject.getId());
//            oneSubject.setTitle(eduSubject.getTitle());
            BeanUtils.copyProperties(eduSubject, oneSubject);

            finalSubjectList.add(oneSubject);

            // 封装一级分类数据
            //在一级分类循环遍历查询所有的二级分类
            //创建list集合封装每一个一级分类的二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            //遍历二级分类list集合
            for (EduSubject tSubject : twoSubjectList) {
                //获取每一个二级分类
                //判断二级分类parentid是否和一级分类id一样
                if (tSubject.getParentId().equals(eduSubject.getId())) {
                    //把tSubject值复制到TwoSubject里面，放到twoFinalSubjectList里面
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject, twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }

            oneSubject.setChildren(twoFinalSubjectList);

        }


        return finalSubjectList;
    }
}
