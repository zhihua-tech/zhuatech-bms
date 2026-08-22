/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bms.service;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingHealthService {
    public HealthResult assess(HealthRequest request) {
        int deductions = Math.min(25, Math.max(0, request.energyDeviation()) * 2)
            + Math.min(30, request.criticalAlarms() * 10)
            + Math.min(20, request.offlineDevices() * 2)
            + Math.min(20, request.maintenanceOverdue() * 4)
            + Math.max(0, 90 - request.comfortRate());
        int score = Math.max(0, 100 - deductions);
        String status = score < 55 || request.criticalAlarms() >= 3 ? "CRITICAL"
            : score < 80 ? "WATCH" : "NORMAL";
        List<String> actions = new ArrayList<>();
        if (request.criticalAlarms() > 0) actions.add("优先处置关键机电和生命安全系统告警");
        if (request.offlineDevices() > 0) actions.add("排查离线网关、控制器与现场传感器");
        if (request.energyDeviation() > 10) actions.add("复核高能耗设备运行时段与控制策略");
        if (request.maintenanceOverdue() > 0) actions.add("安排超期设备维保并记录停机窗口");
        if (request.comfortRate() < 90) actions.add("校准温湿度与新风控制参数");
        if (actions.isEmpty()) actions.add("维持当前策略并持续监测运行趋势");
        return new HealthResult(score, status, actions);
    }

    public record HealthRequest(@NotNull @Min(-100) @Max(100) Integer energyDeviation,
        @NotNull @Min(0) @Max(1000) Integer criticalAlarms,
        @NotNull @Min(0) @Max(10000) Integer offlineDevices,
        @NotNull @Min(0) @Max(100) Integer comfortRate,
        @NotNull @Min(0) @Max(10000) Integer maintenanceOverdue) {}
    public record HealthResult(int score, String status, List<String> actions) {}
}
