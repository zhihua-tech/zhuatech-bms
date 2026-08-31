# 企业级楼宇关键告警响应

[知华科技（上海如静知华信息科技有限公司）](https://www.zhuatech.cn/)为 BMS 开源版增加关键告警响应门禁。

`POST /api/enterprise/bms/critical-alarm-response` 检查告警确认、工程师、安全状态、疏散、冗余、维修工单和响应 SLA，返回 `RESPOND / ESCALATE / BLOCKED`。

企业部署应联动消防、门禁、广播、工单和应急预案，并完整保存告警响应时间线。
