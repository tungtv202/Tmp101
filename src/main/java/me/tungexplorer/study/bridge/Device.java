package me.tungexplorer.study.bridge;

abstract class EntertainmentDevice {
    public int deviceState;
    public int maxSetting;
    public int volumeLevel = 0;

    public abstract void buttonFivePressed();

    public abstract void buttonSixPressed();

    public void deviceFeedback() {
        if (deviceState > maxSetting || deviceState < 0) {
            deviceState = 0;
        }
        System.out.println("On " + deviceState);
    }

    public void buttonSevenPressed() {
        volumeLevel++;
        System.out.println("Volume at: " + volumeLevel);
    }

    public void buttonEightPressed() {
        volumeLevel--;
        System.out.println("Volume at: " + volumeLevel);
    }
}

class TVDevice extends EntertainmentDevice {

    public TVDevice(int newDeviceState, int newMaxSetting) {
        deviceState = newDeviceState;
        maxSetting = newMaxSetting;
    }

    @Override
    public void buttonFivePressed() {
        System.out.println("Channel Down");
        deviceState--;
    }

    @Override
    public void buttonSixPressed() {
        System.out.println("Channel Up");
        deviceState++;
    }
}

class DVDDevice extends EntertainmentDevice {

    @Override
    public void buttonFivePressed() {
        //todo
    }

    @Override
    public void buttonSixPressed() {
        //todo
    }
}