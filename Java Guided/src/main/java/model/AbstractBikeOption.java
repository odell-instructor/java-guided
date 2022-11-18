package model;

import main.FrameColors;

public abstract class AbstractBikeOption extends AbstractBike {
    protected AbstractBike decoratedBike;

    public AbstractBikeOption(AbstractBike bike) {
        super(bike.getColor());
        decoratedBike = bike;
    }

    public abstract String getOptionName();
    public abstract double getOptionAmount();

    @Override
    public String getSerial() {
        return decoratedBike.getSerial();
    }

    @Override
    public String toString() {
        String price = String.format("%24s", "$"+getPrice());
        return "  "+decoratedBike.getClass().getSimpleName() + price;
    }
}
