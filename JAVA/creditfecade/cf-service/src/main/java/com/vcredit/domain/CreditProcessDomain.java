package com.vcredit.domain;

import com.vcredit.persistence.mapper.CreditConfigMapper;
import com.vcredit.persistence.mapper.DicitionaryMapper;
import com.vcredit.persistence.po.CreditConfig;
import com.vcredit.persistence.po.Dicitionary;
import enums.UseStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author chenyubin
 * @Date 2018/7/19
 */
@Component
@Slf4j
public class CreditProcessDomain {

    private static final long DICITIONARY_ID_ITEM_PROJECT = 8L;//字典表内约定的项目项的id

    private static final long DICITIONARY_ID_ITEM_STEP = 2l;

    @Autowired
    private DicitionaryMapper dicitionaryMapper;
    @Autowired
    private CreditConfigMapper creditConfigMapper;

    private CreditConfigComparator creditConfigComparator = new CreditConfigComparator();
    /**
     * 获取字典表内的项目名称配置
     * @return
     */
    public List<String> creditConfigProjectList() {
        Dicitionary projectItem =  dicitionaryMapper.findById(DICITIONARY_ID_ITEM_PROJECT);
        Dicitionary condition = new Dicitionary().setParentId(projectItem.getId());
        List<Dicitionary> projectList = dicitionaryMapper.find(condition);
        List<String> names = new ArrayList<>();
        if(projectList == null || projectList.isEmpty() ) return names;
        for(Dicitionary project : projectList){
            names.add(project.getValue());
        }
        return names;
    }

    /**
     * 获取已经存在授信流程配置的项目名
     * @return
     */
    public List<String> queryExistsProjectProcessName(){
        List<CreditConfig> configs = creditConfigMapper.find( new CreditConfig().setSeqNo(1) );
        List<String> names = new ArrayList<>();
        if (configs == null || configs.isEmpty() )
            return names;
        for (CreditConfig config : configs) {
            names.add(config.getProjectName());
        }
        return names;
    }

    /**
     * 获取所有已存在步骤的名称
     * @return
     */
    public List<String> getAllExistsStepNames() {
        List<Dicitionary> dicitionarys = dicitionaryMapper.find( new Dicitionary().setParentId(DICITIONARY_ID_ITEM_STEP) );
        List<String> names = new ArrayList<>();
        if (dicitionarys == null || dicitionarys.isEmpty() )
            return names;
        for (Dicitionary temp : dicitionarys) {
            names.add(temp.getValue());
        }
        return names;
    }

    /**
     * 根据项目名、步骤名查找对应的流程配置
     * @param config
     * @return
     */
    public List<CreditConfig> findProjectStep(CreditConfig config){
        Assert.notNull(config, "作为条件的config不能为null");
        Assert.hasText(config.getProjectName(), "作为条件的projectName不能为空");
        Assert.hasText(config.getStepName(), "作为条件的stepName不能为空");
        CreditConfig condition = new CreditConfig();
        condition.setProjectName(config.getProjectName());
        condition.setStepName(config.getStepName());
        return creditConfigMapper.find(condition);
    }

    /**
     * 向流程添加新步骤
     * @param config
     * @return 返会序号
     */
    public int addNewStep2Process(CreditConfig config){
        List<CreditConfig> configs = creditConfigMapper.findOrderConfigByName(config.getProjectName());
        if (configs == null) {
            config.setSeqNo(1);
        } else {
            config.setSeqNo(configs.size() + 1);
        }
        config.setUseStatus(UseStatusEnum.INUSE.getCode());
        creditConfigMapper.insert(config);
        return config.getSeqNo();
    }

    public List<CreditConfig> getFullConfigInfoByProject(String projectName) {
        List<CreditConfig> configs = creditConfigMapper.findOrderConfigByName(projectName);
        configs.sort( this.creditConfigComparator );
        return configs;
    }

    /**
     * 将授信流程从新排序
     * @param id
     * @param index
     * @return 重新排序后的流程
     */
    @Transactional
    public List<CreditConfig> resortProcess(Long id, int index){
        CreditConfig config = creditConfigMapper.findById(id);

        List<CreditConfig> list = creditConfigMapper.find( new CreditConfig().setProjectName(config.getProjectName()) );
        list.sort( this.creditConfigComparator );
        int oldIndex = config.getSeqNo() - 1;
        list.remove(oldIndex);
        list.add(index - 1,config);

        CreditConfig cc;
        for(int i=0,size=list.size(); i<size ; i++){
            cc = list.get(i);
            if( cc.getSeqNo().equals( i + 1) ) continue;
            cc.setSeqNo(i + 1);
            log.info(cc.toString());
            creditConfigMapper.update(cc);
        }
        return list;
    }
    /**
     * 更改授信流程应用状态
     * @param id
     * @param index
     */    
	public void changeProcessUseStatus(Long id,boolean flag) {
		CreditConfig config = creditConfigMapper.findById(id);
		if (config.getUseStatus().equals(UseStatusEnum.INUSE.getCode()) && !flag) {
			config.setUseStatus(UseStatusEnum.INVALID.getCode());
		} else if (config.getUseStatus().equals(UseStatusEnum.INVALID.getCode()) && flag) {
			config.setUseStatus(UseStatusEnum.INUSE.getCode());
		} else {
			log.error("应用状态未改变");
			return;
		}
		creditConfigMapper.update(config);
	}

    public void changeUseStatus(Long id,UseStatusEnum status) {
        CreditConfig config = creditConfigMapper.findById(id);
        config.setUseStatus(status.getCode());
        creditConfigMapper.update(config);
    }


    private static class CreditConfigComparator implements Comparator<CreditConfig>{

        private CreditConfigComparator() {}

        @Override
        public int compare(CreditConfig o1, CreditConfig o2) {
            if(o1==null || o2==null) return 0;
            Integer aSeqNo = o1.getSeqNo();
            Integer bSeqNo = o2.getSeqNo();
            if(aSeqNo == null || bSeqNo == null ) return 0;
            return aSeqNo.compareTo(bSeqNo);
        }

    }
}
