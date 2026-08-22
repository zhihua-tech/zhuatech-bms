/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bms.domain;
import org.springframework.stereotype.Component;
import java.util.List;
@Component public class DomainCatalog {
    public String systemName(){return "知华 BMS 智慧楼宇运营管理平台";}
    public String sceneName(){return "楼宇设备、能源计量、环境舒适度、告警联动与运维工单";}
    public List<SeedItem> seedItems(){return List.of(
        new SeedItem("BMS-20260801-001","A 座冷冻站高压告警处置","处理中","暖通运维组","紧急"),
        new SeedItem("BMS-20260801-002","三层会议区舒适度偏差复核","待处理","环境管理组","中"),
        new SeedItem("BMS-20260801-003","夜间照明策略节能优化","处理中","能源管理组","高"),
        new SeedItem("BMS-20260801-004","消防联动设备月检确认","已完成","安全运行组","高"));}
    public List<String> recommendedActions(){return List.of("优先处理生命安全与关键机电告警","排查离线设备和超期维保工单","按分区优化能耗策略与舒适度设定");}
    public record SeedItem(String recordNo,String title,String status,String owner,String priority){}
}
