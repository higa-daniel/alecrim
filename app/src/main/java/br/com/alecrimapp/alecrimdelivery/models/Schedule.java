package br.com.alecrimapp.alecrimdelivery.models;

import java.io.Serializable;

/**
 * Created by ricardomiranda on 09/01/16.
 */
public class Schedule implements Serializable {
    private long scheduleId;
    private int etaInMinutes;

    public long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getEtaInMinutes() {
        return etaInMinutes;
    }

    public void setEtaInMinutes(int etaInMinutes) {
        this.etaInMinutes = etaInMinutes;
    }
}
