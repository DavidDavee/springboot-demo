package com.headline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.headline.domain.Type;
import com.headline.service.TypeService;
import com.headline.mapper.TypeMapper;
import org.springframework.stereotype.Service;

/**
* @author David
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-06-07 17:33:10
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

}




