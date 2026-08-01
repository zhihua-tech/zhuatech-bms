# 架构说明

```text
Vue 3 管理端 / 响应式 H5
          │ HTTP / JSON
Spring Security → Controller → Service → Spring Data JPA → MySQL 8
                                  │
                    楼宇健康度与告警处置规则引擎
```

当前版本以单体分层架构保证易运行与易理解。`DomainCatalog` 管理楼宇运维样例，`BuildingHealthService` 聚合设备、能耗、告警与舒适度指标，`WorkItem` 承载处置事项。生产化时建议通过边缘网关接入 BACnet、Modbus 或 MQTT，并增加时序数据、告警抑制、控制指令双确认和网络分区。
