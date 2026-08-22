/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bms;

import cn.zhuatech.bms.service.IndoorComfortControlService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IndoorComfortControlServiceTests {
    private final IndoorComfortControlService service = new IndoorComfortControlService();

    @Test
    void requiresImmediateVentilationForHighCo2() {
        var result = service.evaluate(new IndoorComfortControlService.Request(
            "MEETING-08", 25, 58, 1700, 18, true, false, 32));

        assertEquals("VENTILATE_NOW", result.decision());
        assertEquals(55, result.comfortScore());
    }

    @Test
    void keepsComfortableOccupiedZoneNormal() {
        var result = service.evaluate(new IndoorComfortControlService.Request(
            "OFFICE-03", 24, 50, 650, 20, true, false, 30));

        assertEquals("NORMAL", result.decision());
        assertEquals(100, result.comfortScore());
    }
}
