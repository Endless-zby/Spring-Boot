package com.zby.recruit.servive;

import com.zby.recruit.dao.CompanyDao;
import com.zby.recruit.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;

    public List<Company> popularList(String isPopular){
        return companyDao.findByIsPopular(isPopular);
    }
}
