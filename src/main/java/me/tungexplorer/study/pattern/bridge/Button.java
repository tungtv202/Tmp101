package me.tungexplorer.study.pattern.bridge;

abstract class RemoteButton {
    private final EntertainmentDevice theDevice;

    public RemoteButton(EntertainmentDevice device) {
        theDevice = device;
    }

    public void buttonFivePressed() {
        theDevice.buttonFivePressed();
    }

    public void buttonSixPressed() {
        theDevice.buttonSixPressed();
    }

    public void deviceFeedback() {
        theDevice.deviceFeedback();
    }

    public abstract void buttonNinePressed();

}

class TVRemoteMute extends RemoteButton {

    public TVRemoteMute(EntertainmentDevice device) {
        super(device);
    }

    @Override
    public void buttonNinePressed() {
        System.out.println("TV was Muted");
    }
}

class TVRemotePause extends RemoteButton {

    public TVRemotePause(EntertainmentDevice device) {
        super(device);
    }

    @Override
    public void buttonNinePressed() {
        System.out.println("TV was Paused");
    }
}