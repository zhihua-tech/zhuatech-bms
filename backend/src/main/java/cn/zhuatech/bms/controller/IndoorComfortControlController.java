/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bms.controller;

import cn.zhuatech.bms.common.ApiResponse;
import cn.zhuatech.bms.service.IndoorComfortControlService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bms/insights")
public class IndoorComfortControlController {
    private final IndoorComfortControlService service;

    public IndoorComfortControlController(IndoorComfortControlService service) {
        this.service = service;
    }

    @PostMapping("/indoor-comfort-control")
    public ApiResponse<IndoorComfortControlService.Result> evaluate(
        @Valid @RequestBody IndoorComfortControlService.Request request) {
        return ApiResponse.ok(service.evaluate(request));
    }
}
