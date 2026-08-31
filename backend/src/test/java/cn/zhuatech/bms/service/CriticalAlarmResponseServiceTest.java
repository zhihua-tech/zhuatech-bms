/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bms.service;import org.junit.jupiter.api.Test;import static org.assertj.core.api.Assertions.assertThat;
class CriticalAlarmResponseServiceTest{private final CriticalAlarmResponseService service=new CriticalAlarmResponseService();
@Test void respondsToControlledAlarm(){var r=service.assess(new CriticalAlarmResponseService.Request("A1",true,true,true,false,false,true,true,5,15));assertThat(r.decision()).isEqualTo(CriticalAlarmResponseService.Decision.RESPOND);}
@Test void blocksUnsafeResponse(){var r=service.assess(new CriticalAlarmResponseService.Request("A2",false,false,false,true,false,true,true,1,15));assertThat(r.blockers()).hasSize(4);}
@Test void escalatesContinuityAndSlaRisk(){var r=service.assess(new CriticalAlarmResponseService.Request("A3",true,true,true,false,false,false,false,20,15));assertThat(r.actions()).hasSize(3);assertThat(r.decision()).isEqualTo(CriticalAlarmResponseService.Decision.ESCALATE);}}
