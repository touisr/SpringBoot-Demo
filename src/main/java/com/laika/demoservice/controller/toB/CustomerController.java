package com.laika.demoservice.controller.toB;
import com.laika.demoservice.core.api.ApiResult;
import com.laika.demoservice.core.api.ApiResultGenerator;
import com.laika.demoservice.model.Customer;
import com.laika.demoservice.service.ICustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
* @author: 会跳舞的机器人
* @date: 2017/12/21 13:58:15
* @description: Customer控制器
*/
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Resource
    private ICustomerService customerService;


    /**
     * 新增Customer
     */
    @PostMapping("/add")
    public ApiResult add(Customer customer) {
        boolean flag = customerService.save(customer);
        if (!flag) {
            return ApiResultGenerator.badParameter("新增失败！");
        }
        return ApiResultGenerator.success();
    }

    /**
     * 删除Customer
     */
    @DeleteMapping("/delete")
    public ApiResult delete(@RequestParam Integer id) {
        boolean flag = customerService.deleteById(id);
        if (!flag) {
            return ApiResultGenerator.badParameter("被删除对象不存在！");
        }
        return ApiResultGenerator.success();
    }

    /**
     * 更新Customer
     */
    @PutMapping("/update")
    public ApiResult update(Customer customer) {
        boolean flag = customerService.update(customer);
        if (!flag) {
            return ApiResultGenerator.badParameter("更新失败！");
        }
        return ApiResultGenerator.success();
    }

    /**
     * Customer详情
     */
    @GetMapping("/detail")
    public ApiResult detail(@RequestParam Integer id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return ApiResultGenerator.badParameter("查找对象不存在！");
        }
        return ApiResultGenerator.success(customer);
    }

    /**
     * Customer分页列表
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     */
    @GetMapping("/pageList")
    public ApiResult pageList(Customer customer, @RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "0") Integer pageSize) {
        // 分页
        PageHelper.startPage(pageNum, pageSize);
        // 增加查询条件，criteria.andCondition("xxxx");
        Condition condition = new Condition(Customer.class);
        Example.Criteria criteria = condition.createCriteria();

        List<Customer> list = customerService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ApiResultGenerator.success(pageInfo);
    }
}
