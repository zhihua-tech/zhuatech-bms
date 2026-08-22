/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bms.service;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndoorComfortControlService {
    public Result evaluate(Request request) {
        int risk = 0;
        if (request.temperatureCelsius() < 20 || request.temperatureCelsius() > 26) risk += 25;
        if (request.humidityPercent() < 35 || request.humidityPercent() > 65) risk += 20;
        if (request.co2Ppm() >= 1000) risk += request.co2Ppm() >= 1500 ? 45 : 25;
        if (request.equipmentFault()) risk += 40;
        if (request.occupancy() == 0 && request.hvacRunning()) risk += 10;
        risk = Math.min(100, risk);
        String decision = request.co2Ppm() >= 1500 ? "VENTILATE_NOW"
            : request.equipmentFault() ? "MAINTENANCE"
            : risk >= 25 ? "ADJUST" : "NORMAL";
        double suggestedSetpoint = request.occupancy() == 0 ? 28
            : request.temperatureCelsius() > 26 ? 24
            : request.temperatureCelsius() < 20 ? 22 : request.temperatureCelsius();

        List<String> actions = new ArrayList<>();
        if (request.co2Ppm() >= 1000) actions.add("提高新风量并检查回风与人员密度");
        if (request.humidityPercent() < 35 || request.humidityPercent() > 65) actions.add("调整加湿或除湿策略");
        if (request.equipmentFault()) actions.add("创建暖通设备维修工单并启用备用控制策略");
        if (request.occupancy() == 0 && request.hvacRunning()) actions.add("切换无人节能模式");
        if (actions.isEmpty()) actions.add("保持当前控制参数并持续采集环境数据");
        return new Result(request.zoneCode(), 100 - risk, decision,
            suggestedSetpoint, actions);
    }

    public record Request(@NotBlank String zoneCode,
                          @DecimalMin("-20") @DecimalMax("60") double temperatureCelsius,
                          @DecimalMin("0") @DecimalMax("100") double humidityPercent,
                          @Min(0) int co2Ppm, @Min(0) int occupancy,
                          boolean hvacRunning, boolean equipmentFault,
                          @DecimalMin("-40") @DecimalMax("60") double outdoorTemperatureCelsius) {}

    public record Result(String zoneCode, int comfortScore, String decision,
                         double suggestedSetpointCelsius, List<String> actions) {}
}
