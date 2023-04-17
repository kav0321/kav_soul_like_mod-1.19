package net.kav.kav_soul_like.networking.packet;

public enum direction {
    LEFT(1),
    RIGHT(2),
    FORWARD(3),
    BACKWARD(4);

    int dir;
    direction(int dir)
    {
        this.dir=dir;
    }

   public int getint()
    {
        return this.dir;
    }
}
