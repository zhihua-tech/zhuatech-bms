/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bms.service;
import jakarta.validation.constraints.Min;import jakarta.validation.constraints.NotBlank;import org.springframework.stereotype.Service;import java.util.*;
@Service
public class CriticalAlarmResponseService {
    public Assessment assess(Request r){List<String>b=new ArrayList<>(),a=new ArrayList<>();
        if(!r.alarmAcknowledged())b.add("关键告警尚未确认");if(!r.technicianAssigned())b.add("未指定处置工程师");
        if(!r.assetInSafeState())b.add("受影响设备未进入安全状态");if(r.evacuationRequired()&&!r.evacuationCompleted())b.add("必要疏散尚未完成");
        if(!b.isEmpty()){a.add("升级至楼宇应急响应并持续广播安全状态");return new Assessment(Decision.BLOCKED,b,a);}
        if(!r.redundancyAvailable()||!r.workOrderCreated()||r.elapsedMinutes()>=r.responseSlaMinutes()){
            if(!r.redundancyAvailable())a.add("启用业务连续性或临时环境保障方案");if(!r.workOrderCreated())a.add("创建关联维修工单");if(r.elapsedMinutes()>=r.responseSlaMinutes())a.add("升级 SLA 违约风险");return new Assessment(Decision.ESCALATE,b,a);}
        a.add("按应急工单继续处置并记录告警时间线");return new Assessment(Decision.RESPOND,b,a);}
    public record Request(@NotBlank String alarmId,boolean alarmAcknowledged,boolean technicianAssigned,boolean assetInSafeState,boolean evacuationRequired,boolean evacuationCompleted,boolean redundancyAvailable,boolean workOrderCreated,@Min(0)int elapsedMinutes,@Min(1)int responseSlaMinutes){}
    public record Assessment(Decision decision,List<String> blockers,List<String> actions){} public enum Decision{RESPOND,ESCALATE,BLOCKED}
}
