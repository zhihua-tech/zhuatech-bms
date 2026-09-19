/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bms.controller;

import cn.zhuatech.bms.common.ApiResponse;
import cn.zhuatech.bms.service.BuildingHealthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/admin/building-health")
public class BuildingHealthController {
    private final BuildingHealthService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public BuildingHealthController(BuildingHealthService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping
    ApiResponse<BuildingHealthService.HealthResult> assess(
        @Valid @RequestBody BuildingHealthService.HealthRequest request) {
        return ApiResponse.ok(service.assess(request));
    }
}
