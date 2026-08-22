/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bms.controller;

import cn.zhuatech.bms.common.ApiResponse;
import cn.zhuatech.bms.service.BuildingHealthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/building-health")
public class BuildingHealthController {
    private final BuildingHealthService service;
    public BuildingHealthController(BuildingHealthService service) { this.service = service; }
    @PostMapping
    ApiResponse<BuildingHealthService.HealthResult> assess(
        @Valid @RequestBody BuildingHealthService.HealthRequest request) {
        return ApiResponse.ok(service.assess(request));
    }
}
