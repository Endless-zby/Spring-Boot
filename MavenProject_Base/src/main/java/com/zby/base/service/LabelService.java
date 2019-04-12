package com.zby.base.service;

import com.zby.base.dao.LabelDao;
import com.zby.base.entity.Label;
import com.zby.entity.PageResult;
import com.zby.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    //查询全部
    public List<Label> findAll(){
        return labelDao.findAll();//JPA提供的
    }
    //增加
    public void save(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }
    //删除
    public void deletebyid(String id){
        labelDao.deleteById(id);
    }
    //修改
    public void update(Label label){
        labelDao.save(label);
    }
    //查询ByID
    public Label queryByid(String id){
        return labelDao.findById(id).get();
    }
    //查询--->查询条件构建
    public Specification<Label> createSpecification(Map queryMap){
        return new Specification<Label>(){

            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                ArrayList<Predicate> predicates = new ArrayList<>();

                if (queryMap.get("labelname") != null && !queryMap.get("labelname").equals("")){

                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%"+(String) queryMap.get("labelname")+"%");
                    predicates.add(predicate);
                }
                if (queryMap.get("recommend") != null && !queryMap.get("recommend").equals("")){

                    Predicate predicate = criteriaBuilder.like(root.get("recommend").as(String.class), "%"+queryMap.get("recommend")+"%");
                    predicates.add(predicate);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    //具体的条件查询操作--->使用createSpecification
    //模糊查询labelname、recommend
    public List<Label> findLabels(Map queryMap){
        Specification<Label> specification = createSpecification(queryMap);
        return labelDao.findAll(specification);
    }

    //条件分页查询
    public Page findLabels(Map queryMap,int start,int pagesize){

        Specification<Label> specification = createSpecification(queryMap);
        PageRequest pageRequest = PageRequest.of(start - 1, pagesize);
        return labelDao.findAll(specification,pageRequest);
    }


}
