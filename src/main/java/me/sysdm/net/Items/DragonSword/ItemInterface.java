package me.sysdm.net.Items.DragonSword;

public interface ItemInterface {

    default void setup() {
    }

    default void setupItems() {
        this.setup();
    }

}
