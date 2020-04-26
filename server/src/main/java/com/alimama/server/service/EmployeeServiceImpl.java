package com.alimama.server.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alimama.api.model.Employee;
import com.alimama.api.model.EmployeeExample;
import com.alimama.api.pipeLine.PipeLine;
import com.alimama.api.service.IEmployeeService;
import com.alimama.server.chains.CommandChain;
import com.alimama.server.context.DataConformContext;
import com.alimama.server.mapper.EmployeeMapper;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ContextBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by PengWX on 2019/4/23.
 */
@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    // 定义的资源
    public static final String USER_RES = "userResource";


    /**
     * 管道校验
     */
    @Autowired
    protected PipeLine.Pipeline<DataConformContext, Exception> submitPipeline;


    /**
     * @return
     * @SentinelResource(value = "getAllEmployee", blockHandler = "exceptionHandler")
     * value资源名,必填
     * blockHandler 抛出的BlockException处理方法,该方法必须是public 返回和被注解的方法相同(getAllEmployee)为List<Employee>,且handler方法参数也要和
     * 被注解的方法相同另外加上BlockException e参数,参考exceptionHandler()方法
     */
    @Override
//    @Cacheable(value = "employeeListCache", keyGenerator = "keyGenerator") //缓存
//    @SentinelResource(value = USER_RES, blockHandler = "exceptionHandler") //sentinel 限流
    public List<Employee> getAllEmployee() throws Exception {

        /**
         * 不要删除 测试使用 apache.commons.chain 来实现链式逻辑
         */
        /*CommandChain commandChain = new CommandChain();
        Context context = new ContextBase();
        context.put("k1", null);
        boolean execute = commandChain.execute(context);*/


        //不要删除  简单使用管道校验参数是否为空
       /* DataConformContext context1 = new DataConformContext();
        context1.setBusinessId(null);
        submitPipeline.handle(context1);*/


        EmployeeExample employeeExample = new EmployeeExample();
        List<Employee> employees = employeeMapper.selectByExample(employeeExample);
        return employees;
    }

    @Override
    public Integer addEmployee(@Valid Employee employee) {
        return employeeMapper.insert(employee);
    }

    @Override
    public List<Employee> selectByExample(EmployeeExample example) {
        return employeeMapper.selectByExample(example);
    }

    @Override
    public Integer deleteById(Long id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    /**
     * getAllEmployee方法上面加了限流,会抛出BlockException,使用
     *
     * @param epId
     * @param e
     * @return
     */
    /**
     * SentinelResource：blockHandler
     * 处理 BlockException 的方法名，可选项。若未配置，则将 BlockException 直接抛出。
     * <p>
     * blockHandler 函数访问范围需要是 public
     * 返回类型需要与原方法相匹配
     * 参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 BlockException
     * blockHandler 函数默认需要和原方法在同一个类中
     * 如果你不想让异常处理方法跟业务方法在同一个类中，可以使用 blockHandlerClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
     *
     * @param epId
     * @param e
     * @return
     */
    public List<Employee> exceptionHandler(Long epId, BlockException e) {
        System.out.println("========================================");
        System.out.println("限流了,怎么办???");
        System.out.println("========================================");
        return new ArrayList<Employee>();
    }

}
