package ${package_service_impl};
import ${package_mapper}.${Table}Mapper;
import ${package_pojo}.${Table};
import ${package_service}.${Table}Service;
import cn.lx.tensquare.utils.IdWorker;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:shenkunlin
 * @Description:${Table}业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class ${Table}ServiceImpl implements ${Table}Service {

    @Autowired
    private ${Table}Mapper ${table}Mapper;

    @Autowired
    private IdWorker idWorker;


    /**
     * ${Table}条件+分页查询
     * @param ${table} 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<${Table}> findPage(${Table} ${table}, int page, int size){
        //分页
        Page<${Table}> page1 = new Page<>(page, size);
        //搜索条件构建
        QueryWrapper<${Table}> queryWrapper = createWrapper(${table});
        //执行搜索
        Page<${Table}> pageInfo = (Page<${Table}>) ${table}Mapper.selectPage(page1, queryWrapper);
        return pageInfo;
    }

    /**
     * ${Table}分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<${Table}> findPage(int page, int size){
        //静态分页
        Page<${Table}> page1 = new Page<>(page, size);
        //分页查询
        QueryWrapper<${Table}> queryWrapper = new QueryWrapper<>();
        Page<${Table}> pageInfo = (Page<${Table}>) ${table}Mapper.selectPage(page1, queryWrapper);
        return pageInfo;
    }

    /**
     * ${Table}条件查询
     * @param ${table}
     * @return
     */
    @Override
    public List<${Table}> findList(${Table} ${table}){
        //构建查询条件
        QueryWrapper<${Table}> queryWrapper = createWrapper(${table});
        //根据构建的条件查询数据
        return ${table}Mapper.selectList(queryWrapper);
    }


    /**
     * ${Table}构建查询对象
     * @param ${table}
     * @return
     */
    public QueryWrapper<${Table}> createWrapper(${Table} ${table}){
        QueryWrapper<${Table}> queryWrapper = new QueryWrapper<>();
        if(${table}!=null){
            <#list models as md>
            // ${md.desc}
            if(!StringUtils.isEmpty(${table}.get${md.upperName}())){
                <#if (md.name?contains("title") || md.name?contains("name"))>
                    queryWrapper.eq("${md.name}","%"+${table}.get${md.upperName}()+"%");
                <#else>
                    queryWrapper.eq("${md.name}",${table}.get${md.upperName}());
                </#if>
            }
            </#list>
        }
        return queryWrapper;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(${keyType} id){
        ${table}Mapper.deleteById(id);
    }

    /**
     * 修改${Table}
     * @param ${table}
     */
    @Override
    public void update(${Table} ${table}){
        ${table}Mapper.updateById(${table});
    }

    /**
     * 增加${Table}
     * @param ${table}
     */
    @Override
    public void add(${Table} ${table}){
        //设置主键值
        ${table}.${keySetMethod}(idWorker.nextId()+"");
        ${table}Mapper.insert(${table});
    }

    /**
     * 根据ID查询${Table}
     * @param id
     * @return
     */
    @Override
    public ${Table} findById(${keyType} id){
        return  ${table}Mapper.selectById(id);
    }

    /**
     * 查询${Table}全部数据
     * @return
     */
    @Override
    public List<${Table}> findAll() {
        return ${table}Mapper.selectList(null);
    }
}
