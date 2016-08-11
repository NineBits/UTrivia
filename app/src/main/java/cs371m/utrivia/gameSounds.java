package cs371m.utrivia;

/**
 * Created by kellypc on 8/10/2016.
 */ class gameSounds {
    public enum SoundStatus {on,off}
    private SoundStatus status;
    public gameSounds() {
        status = SoundStatus.on;
    }
    public gameSounds(SoundStatus s) {
        status = s;
    }

    public void setSoundStatus (SoundStatus s) { status = s;}
    public SoundStatus getSoundStatus() { return status;}
}
