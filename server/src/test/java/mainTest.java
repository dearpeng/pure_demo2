import com.alibaba.fastjson.JSON;
import com.alimama.api.model.Employee;
import com.alimama.api.model.EmployeeVo;
import com.alimama.api.service.IEmployeeMapper;

import java.util.Date;

/**
 * Created by PengWX on 2019/8/29.
 */
public class mainTest {

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setLastName("张三");
        employee.setBirth(new Date());
        employee.setEmail("123123@qq.com");
        employee.setDepartmentId(222L);
        employee.setDepartmentName("技术部");
        employee.setPassword("6666666");
        employee.setGender(new Byte("0"));
        employee.setSalt("111111111111");
        EmployeeVo employeeVo = IEmployeeMapper.INSTANCE.employee2EmployeeVo(employee);
        System.out.println(JSON.toJSONString(employeeVo));
    }
}
