/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bms.controller;import cn.zhuatech.bms.common.ApiResponse;import cn.zhuatech.bms.service.CriticalAlarmResponseService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/enterprise/bms") public class CriticalAlarmResponseController{private final CriticalAlarmResponseService service;/**
                                                                                                                                                        * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                        */
public CriticalAlarmResponseController(CriticalAlarmResponseService service){this.service=service;}/**
                                                                                                                                                                                                                                                           * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                           */
@PostMapping("/critical-alarm-response")public ApiResponse<CriticalAlarmResponseService.Assessment>assess(@Valid @RequestBody CriticalAlarmResponseService.Request request){return ApiResponse.ok(service.assess(request));}}
